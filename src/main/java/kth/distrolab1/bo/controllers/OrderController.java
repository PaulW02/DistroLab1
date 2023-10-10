package kth.distrolab1.bo.controllers;


import kth.distrolab1.bo.services.OrderService;
import kth.distrolab1.bo.services.OrderServiceImpl;
import kth.distrolab1.ui.dtos.*;

import java.util.List;

/**
 * The OrderController class provides methods to manage and process orders.
 * It acts as an intermediary between the view and the service layer, handling
 * order-related requests and operations.
 */
public class OrderController {

    private OrderService orderService = new OrderServiceImpl();
    /**
     * Retrieves a summary of all orders.
     *
     * @return OrderStatusDTO containing the status of all orders.
     */
    public OrderStatusDTO viewAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Retrieves details of a specific order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return OrderDTO containing the details of the specified order.
     */
    public OrderDTO viewOrder(int id) {
        return orderService.getOrderById(id);
    }

    /**
     * Creates a new order for a user.
     *
     * @param userId The ID of the user placing the order.
     * @param amount The total amount of the order.
     * @param orderItemDTOS List of items included in the order.
     * @return OrderDTO representing the created order.
     */
    public OrderDTO createOrder(int userId, double amount, List<OrderItemDTO> orderItemDTOS) {
        return orderService.createOrder(userId, amount, orderItemDTOS);
    }

    /**
     * Sends an order for processing.
     *
     * @param id The ID of the order to be sent.
     */
    public void sendOrder(int id){
        orderService.sendOrder(id);
    }

    /**
     * Processes an order based on the user and their shopping bag.
     *
     * @param userDTO The user placing the order.
     * @param shoppingBag List of items the user wishes to order.
     * @return OrderDTO representing the processed order or null if processing failed.
     */
    public OrderDTO processOrder(UserDTO userDTO, List<ItemDTO> shoppingBag) {
        if (userDTO != null && !shoppingBag.isEmpty()){
            return orderService.processOrder(userDTO, shoppingBag);
        }
        return null;
    }
}

