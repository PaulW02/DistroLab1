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

        if (pathInfo.equals("/purchase")) {
            UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");

            if (userDTO != null) {
                List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");

                // Call the service to process the order
                OrderDTO orderDTO = orderController.processOrder(userDTO, shoppingBag);

                if (orderDTO != null) {
                    request.setAttribute("orderDTO", orderDTO);
                    session.setAttribute("shoppingBag", new ArrayList<>());
                    request.getRequestDispatcher("/order.jsp").forward(request, response);
                } else {
                    response.sendRedirect("http://localhost:8080/");
                }
            } else {
                response.sendRedirect(request.getHeader("Referer"));
            }
        }else if(pathInfo.equals("/send")){
            String orderId = request.getParameter("orderId");
            if (orderId != null){
                orderController.sendOrder(Integer.valueOf(orderId));
            }
            response.sendRedirect("http://localhost:8080/employee/");

        }
    }

}
