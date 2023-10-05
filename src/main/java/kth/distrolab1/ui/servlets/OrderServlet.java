package kth.distrolab1.ui.servlets;
import kth.distrolab1.bo.controllers.OrderController;
import kth.distrolab1.ui.dtos.ItemDTO;
import kth.distrolab1.ui.dtos.OrderDTO;
import kth.distrolab1.ui.dtos.OrderItemDTO;
import kth.distrolab1.ui.dtos.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "order", value = "/order/*")
public class OrderServlet extends HttpServlet{

    private OrderController orderController;

    public void init() {
        orderController = new OrderController();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");
        request.setAttribute("shoppingBag", shoppingBag);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();

        if(pathInfo.equals("/purchase")){
            UserDTO userDTO = ((UserDTO) session.getAttribute("userDTO"));
            if (userDTO != null) {
                List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");
                session.setAttribute("shoppingBag", new ArrayList<>());
                List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
                double totalPrice = 0;
                for (ItemDTO shoppingBagItem : shoppingBag) {
                    // Check if an item with the same ID exists in orderItemDTOs
                    boolean itemExists = false;
                    totalPrice += shoppingBagItem.getPrice();
                    for (OrderItemDTO orderItemDTO : orderItemDTOS) {
                        if (orderItemDTO.getItemId() == shoppingBagItem.getId()) {
                            // Item with the same ID exists, increase the quantity
                            orderItemDTO.setItemName(shoppingBagItem.getItemName());
                            orderItemDTO.setQuantity(orderItemDTO.getQuantity() + 1);
                            orderItemDTO.setTotalPrice(orderItemDTO.getTotalPrice() + shoppingBagItem.getPrice());
                            itemExists = true;
                            break;
                        }
                    }

                    if (!itemExists) {
                        // Item with the same ID does not exist, add a new item
                        OrderItemDTO newOrderItemDTO = new OrderItemDTO();
                        newOrderItemDTO.setItemId(shoppingBagItem.getId());
                        newOrderItemDTO.setItemName(shoppingBagItem.getItemName());
                        newOrderItemDTO.setQuantity(1); // Initialize with quantity 1
                        newOrderItemDTO.setTotalPrice(shoppingBagItem.getPrice());
                        orderItemDTOS.add(newOrderItemDTO);
                    }
                }
                if (totalPrice > 0) {
                    OrderDTO orderDTO = orderController.createOrder(userDTO.getId(), totalPrice, orderItemDTOS);
                    request.setAttribute("orderDTO", orderDTO);
                    request.getRequestDispatcher("/order.jsp").forward(request, response);
                }else{
                    response.sendRedirect("http://localhost:8080/index.jsp");
                }
            }else {
                response.sendRedirect(request.getHeader("Referer"));
            }
        }
    }

}
