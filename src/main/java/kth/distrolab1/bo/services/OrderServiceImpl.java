package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Order;
import kth.distrolab1.bo.entities.OrderItem;
import kth.distrolab1.db.repositories.OrderRepository;
import kth.distrolab1.db.repositories.OrderRepositoryImpl;
import kth.distrolab1.ui.dtos.OrderDTO;
import kth.distrolab1.ui.dtos.OrderItemDTO;

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
    public List<OrderDTO> getOrdersForUser(int userId) {
        return null;
    }

    @Override
    public OrderDTO updateOrder(int orderId, OrderDTO order) {
        return null;
    }

    @Override
    public void deleteOrder(int orderId) {

    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAllOrder();
        List<OrderDTO> orderDTOs = new ArrayList<>();

        for (Order order: orders) {
            List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
            for (OrderItem orderItem: order.getItemsBought()) {
                orderItemDTOS.add(new OrderItemDTO(orderItem.getOrderItemId(), orderItem.getOrderId(), orderItem.getItemId(), orderItem.getItemName(), orderItem.getQuantity(), orderItem.getTotalPrice()));
            }
            orderDTOs.add(new OrderDTO(order.getOrderId(), order.getUserId(), order.getPurchaseDate(), order.getAmount(), orderItemDTOS));
        }
        return orderDTOs;
    }

}
