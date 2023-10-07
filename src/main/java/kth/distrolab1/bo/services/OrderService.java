package kth.distrolab1.bo.services;

import kth.distrolab1.ui.dtos.*;

import java.util.List;
public interface OrderService {
    // Create a new order
    OrderDTO createOrder(int userId, double items, List<OrderItemDTO> totalAmount);

    // Retrieve a order by its ID
    OrderDTO getOrderById(int orderId);

    void sendOrder(int orderId);

    // Retrieve all orders (for admin or reporting purposes)
    OrderStatusDTO getAllOrders();

    OrderDTO processOrder(UserDTO userDTO, List<ItemDTO> itemDTOS);

}

