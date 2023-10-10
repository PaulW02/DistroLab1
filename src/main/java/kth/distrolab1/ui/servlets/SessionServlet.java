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

/**
 * Servlet responsible for handling user session-related operations.
 * This servlet provides endpoints for user login, registration, and logout functionalities.
 */
@WebServlet(name = "session", value = "/session/*")
public class SessionServlet extends HttpServlet {

    private UserController userController = new UserController();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    /**
     * Handles GET requests to the session servlet.
     * This method processes user logout based on the provided path info.
     *
     * @param request  The HttpServletRequest object containing client request data.
     * @param response The HttpServletResponse object for sending responses to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/logout")) {
            session.invalidate();
            response.sendRedirect("http://localhost:8080/");
        }

    }
}