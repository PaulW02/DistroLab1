package kth.distrolab1.bo.controllers;


import kth.distrolab1.bo.services.OrderService;
import kth.distrolab1.bo.services.OrderServiceImpl;
import kth.distrolab1.ui.dtos.*;

import java.util.List;

public class OrderController {

    private OrderService orderService = new OrderServiceImpl();

    public OrderStatusDTO viewAllOrders() {
        // Retrieve a list of all orders and pass it to the view
        return orderService.getAllOrders();
    }

    public OrderDTO viewOrder(int id) {
        // Retrieve a specific order by ID and pass it to the view
        OrderDTO orderDTO = orderService.getOrderById(id);
        // Return the view name (e.g., order-details.jsp)
        return orderDTO;
    }

    public OrderDTO createOrder(int userId, double amount, List<OrderItemDTO> orderItemDTOS) {
        return orderService.createOrder(userId, amount, orderItemDTOS);
    }

    public void sendOrder(int id){
        orderService.sendOrder(id);
    }


    public OrderDTO processOrder(UserDTO userDTO, List<ItemDTO> shoppingBag) {
        if (userDTO != null && !shoppingBag.isEmpty()){
            return orderService.processOrder(userDTO, shoppingBag);
        }
        return null;
    }
}

