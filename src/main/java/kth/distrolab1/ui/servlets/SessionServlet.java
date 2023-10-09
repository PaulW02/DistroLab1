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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

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