package kth.distrolab1.bo.services;

import kth.distrolab1.ui.dtos.OrderDTO;
import kth.distrolab1.ui.dtos.OrderItemDTO;

import java.util.List;
public interface OrderService {
    // Create a new order
    OrderDTO createOrder(int userId, double items, List<OrderItemDTO> totalAmount);

    // Retrieve a order by its ID
    OrderDTO getOrderById(int orderId);

    // Retrieve all orders for a specific user
    List<OrderDTO> getOrdersForUser(int userId);

    // Update an existing order

    OrderDTO updateOrder(int orderId, OrderDTO orderDTO);

    // Delete a order by its ID
    void deleteOrder(int orderId);

    // Retrieve all orders (for admin or reporting purposes)
    List<OrderDTO> getAllOrders();

}

