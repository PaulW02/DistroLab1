package kth.distrolab1.bo.controllers;


import kth.distrolab1.bo.services.OrderService;
import kth.distrolab1.bo.services.OrderServiceImpl;
import kth.distrolab1.ui.dtos.OrderDTO;
import kth.distrolab1.ui.dtos.OrderItemDTO;

import java.util.List;

public class OrderController {

    private OrderService orderService = new OrderServiceImpl();

    public List<OrderDTO> viewAllOrders() {
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
    /*
    public String showEditForm(int id) {
        // Retrieve the order to be edited and display its data in an edit form
        OrderDTO order = orderService.getOrderById(id);
        // Return a view for editing the order
        return "edit-order";
    }


    public String updateOrder(int id, OrderDTO updatedOrder) {
        // Update the order with the provided data
        orderService.updateOrder(id, updatedOrder);
        // Redirect to the list of all orders or a success page
        return "redirect:/orders/";
    }
*/

    public String deleteOrder(int id) {
        // Delete the order with the specified ID
        orderService.deleteOrder(id);
        // Redirect to the list of all orders or a success page
        return "redirect:/orders/";
    }
}

