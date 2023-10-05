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


@WebServlet(name = "session", value = "/session/*")
public class SessionServlet extends HttpServlet {

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
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/logout")) {
            // Stäng av sessionen och logga ut användaren
            session.invalidate();
            response.sendRedirect("http://localhost:8080/"); // Du kan omdirigera användaren till inloggningssidan eller någon annan sida efter utloggningen.
        }

    }
}