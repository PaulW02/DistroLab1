package kth.distrolab1.ui.servlets;

import kth.distrolab1.bo.controllers.UserController;
import kth.distrolab1.ui.dtos.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet(name = "user", value = "/user/*")
public class UserServlet extends HttpServlet {

    private UserController userController = new UserController();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/login")){
            UserDTO userDTO = userController.login(request.getParameter("uname"), request.getParameter("pass"));
            if (userDTO != null){
                session.setAttribute("userDTO", userDTO);
                response.sendRedirect("http://localhost:8080/");
            }else{
                String errorMessage = "Invalid username or password";
                request.setAttribute("errorMessage", errorMessage);
                response.sendRedirect("http://localhost:8080/login.jsp");
            }
        } else if (pathInfo.equals("/register")) {
            UserDTO userDTO;
            if (request.getParameter("selectedRoles") != null){
                String rolesParam = request.getParameter("selectedRoles");
                String[] selectedRoles = rolesParam.split(",");
                List<String> rolesList = Arrays.asList(selectedRoles);
                userDTO = userController.createUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"), request.getParameter("fullname"), rolesList);
            }else{
                userDTO = userController.createUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"), request.getParameter("fullname"), new ArrayList<>());
            }
            if (userDTO != null){
                session.setAttribute("userDTO", userDTO);
                session.setAttribute("fullname", userDTO.getFullname());
                response.sendRedirect("http://localhost:8080/");
            }else{
                String errorMessage = "Invalid username or password";
                request.setAttribute("errorMessage", errorMessage);
            }
        }else if (pathInfo.equals("/delete") && session.getAttribute("userDTO") != null && ((UserDTO) session.getAttribute("userDTO")).getRoles().contains("role_admin")) {
            int userId = Integer.valueOf(request.getParameter("userToDelete"));
            // Check if userId is a valid number (you may want to add error handling)
            if (userId > 0) {

                // Call a method to delete the user with userIdInt
                if (userController.deleteUser(userId)) {
                    // User deleted successfully
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("User with ID " + userId + " deleted successfully.");
                    response.sendRedirect("http://localhost:8080/admin/");
                } else {
                    // User not found or couldn't be deleted
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("User with ID " + userId + " not found or couldn't be deleted.");
                }
            } else {
                // Invalid user ID format
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid user ID format.");
            }
        }else{
            response.sendRedirect("http://localhost:8080/");
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}