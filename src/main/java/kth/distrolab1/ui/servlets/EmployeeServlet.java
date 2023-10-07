package kth.distrolab1.ui.servlets;
import kth.distrolab1.bo.controllers.OrderController;
import kth.distrolab1.ui.dtos.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "employee", value = "/employee/*")
public class EmployeeServlet extends HttpServlet{

    private OrderController orderController;

    public void init() {
        orderController = new OrderController();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/")){
            OrderStatusDTO orderStatusDTO = orderController.viewAllOrders();
            request.setAttribute("orderStatusDTO", orderStatusDTO);
            request.getRequestDispatcher("/employeePanel.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();

        if(pathInfo.equals("/send")){
            String orderId = request.getParameter("orderId");
            if (orderId != null){
                orderController.sendOrder(Integer.valueOf(orderId));
            }
            response.sendRedirect("http://localhost:8080/employee/");

        }
    }

}