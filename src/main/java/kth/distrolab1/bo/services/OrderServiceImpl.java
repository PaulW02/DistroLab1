package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Order;
import kth.distrolab1.bo.entities.OrderItem;
import kth.distrolab1.db.repositories.OrderRepository;
import kth.distrolab1.db.repositories.OrderRepositoryImpl;
import kth.distrolab1.ui.dtos.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public OrderDTO createOrder(int userId, double totalAmount, List<OrderItemDTO> orderItemDTOs) {
        // Create a new order entity and set its properties
        Order order = new Order();
        order.setUserId(userId);
        order.setPurchaseDate(new Date());
        order.setAmount(totalAmount);
        order.setOrderSent(false);
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO orderItemDTO: orderItemDTOs) {
            orderItems.add(new OrderItem(orderItemDTO.getOrderItemId(), orderItemDTO.getOrderId(), orderItemDTO.getItemId(), orderItemDTO.getItemName(), orderItemDTO.getQuantity(), orderItemDTO.getTotalPrice()));
        }
        order.setItemsBought(orderItems);
        order = orderRepository.createOrder(order);
        // You can save the order to the database using your repository, e.g., orderRepository.save(order);

        // If the order is successfully saved and has an auto-generated ID, you can set it in the DTO
        if (order.getOrderId() > 0) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setUserId(order.getUserId());
            orderDTO.setAmount(order.getAmount());
            orderDTO.setPurchaseDate(order.getPurchaseDate());
            // Create and set order items for the DTO (if needed)
            List<OrderItemDTO> itemDTOs = new ArrayList<>();
            for (OrderItem item : orderItems) {
                // Convert and set properties from the entity to the DTO
                OrderItemDTO itemDTO = new OrderItemDTO();
                itemDTO.setItemId(item.getItemId());
                itemDTO.setItemName(item.getItemName());
                itemDTO.setTotalPrice(item.getTotalPrice());
                itemDTO.setQuantity(item.getQuantity());
                // Add the item to the list
                itemDTOs.add(itemDTO);
            }
            orderDTO.setItemsBought(itemDTOs);
            return orderDTO;
        } else {
            // Order creation failed, return null or handle the error accordingly
            return null;
        }
    }


    @Override
    public OrderDTO getOrderById(int orderId) {
        return null;
    }

    @Override
    public void sendOrder(int orderId) {
        orderRepository.sendOrder(orderId);
    }

    @Override
    public OrderStatusDTO getAllOrders() {
        List<Order> orders = orderRepository.findAllOrders();
        List<OrderDTO> unsentOrderDTOs = new ArrayList<>();
        List<OrderDTO> sentOrderDTOs = new ArrayList();

        for (Order order : orders) {
            List<OrderItemDTO> orderItemDTOs = new ArrayList<>();

            for (OrderItem orderItem : order.getItemsBought()) {
                orderItemDTOs.add(new OrderItemDTO(orderItem.getOrderItemId(), orderItem.getOrderId(), orderItem.getItemId(), orderItem.getItemName(), orderItem.getQuantity(), orderItem.getTotalPrice()));
            }

            OrderDTO orderDTO = new OrderDTO(order.getOrderId(), order.getUserId(), order.getPurchaseDate(), order.getAmount(), orderItemDTOs);

            if (order.isOrderSent()) {
                sentOrderDTOs.add(orderDTO);
            } else {
                unsentOrderDTOs.add(orderDTO);
            }
        }

        OrderStatusDTO orderStatusDTO = new OrderStatusDTO(unsentOrderDTOs, sentOrderDTOs);

        return orderStatusDTO;

    }

    @Override
    public OrderDTO processOrder(UserDTO userDTO, List<ItemDTO> shoppingBag) {
        if (userDTO == null || shoppingBag == null || shoppingBag.isEmpty()) {
            return null;
        }

        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        double totalPrice = 0;
        boolean canPlaceOrder = true;

        for (ItemDTO shoppingBagItem : shoppingBag) {
            int shoppingBagQuantity = shoppingBagItem.getQuantity();
            int orderedQuantity = 0;

            // Check if an item with the same ID exists in orderItemDTOs
            boolean itemExists = false;
            totalPrice += shoppingBagItem.getPrice();

            for (OrderItemDTO orderItemDTO : orderItemDTOS) {
                if (orderItemDTO.getItemId() == shoppingBagItem.getId()) {
                    orderedQuantity = orderItemDTO.getQuantity();
                    orderItemDTO.setItemName(shoppingBagItem.getItemName());
                    orderItemDTO.setQuantity(orderItemDTO.getQuantity() + 1);
                    orderItemDTO.setTotalPrice(orderItemDTO.getTotalPrice() + shoppingBagItem.getPrice());
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                OrderItemDTO newOrderItemDTO = new OrderItemDTO();
                newOrderItemDTO.setItemId(shoppingBagItem.getId());
                newOrderItemDTO.setItemName(shoppingBagItem.getItemName());
                newOrderItemDTO.setQuantity(1);
                newOrderItemDTO.setTotalPrice(shoppingBagItem.getPrice());
                orderItemDTOS.add(newOrderItemDTO);
            }

            if (shoppingBagQuantity - (orderedQuantity + 1) < 0) {
                canPlaceOrder = false;
                break;
            }
        }

        if (canPlaceOrder && totalPrice > 0) {
            // You can create the OrderDTO here or in your controller
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUserId(userDTO.getId());
            orderDTO.setAmount(totalPrice);
            orderDTO.setItemsBought(orderItemDTOS);

            // Optionally, you can perform additional order-related logic here
            return createOrder(orderDTO.getUserId(), orderDTO.getAmount(), orderDTO.getItemsBought());
        } else {
            return null;
        }
    }

}
