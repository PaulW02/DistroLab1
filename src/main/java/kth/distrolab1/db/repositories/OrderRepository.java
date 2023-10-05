package kth.distrolab1.db.repositories;

import kth.distrolab1.bo.entities.Order;

import java.util.List;

public interface OrderRepository {
    // Create a new order
    Order createOrder(Order order);

    // Retrieve a order by its ID
    Order findOrderById(int orderId);

    // Retrieve all orders for a specific user
    List<Order> findOrdersForUser(int userId);

    // Update an existing order
    Order updateOrder(Order order);

    // Delete a order by its ID
    void deleteOrder(int orderId);

    // Retrieve all orders (for admin or reporting purposes)
    List<Order> findAllOrder();
}
