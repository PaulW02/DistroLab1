package kth.distrolab1.bo.servlets;

import kth.distrolab1.bo.handlers.UserHandler;
import kth.distrolab1.ui.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "session", value = "/session/*")
public class SessionServlet extends HttpServlet {

    private UserHandler userHandler = new UserHandler();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/login")){
            UserDTO userDTO = userHandler.login(request.getParameter("uname"), request.getParameter("pass"));
            if (userDTO != null){
                session.setAttribute("userDTO", userDTO);
                session.setAttribute("fullname", userDTO.getFullname());
                response.sendRedirect("http://localhost:8080/DistroLab1_war_exploded/index.jsp");
            }else{
                String errorMessage = "Invalid username or password";
                request.setAttribute("errorMessage", errorMessage);
                response.sendRedirect("http://localhost:8080/DistroLab1_war_exploded/login.jsp");
            }
        } else if (pathInfo.equals("/register")) {
            UserDTO userDTO = userHandler.createUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"), request.getParameter("fullname"));
            if (userDTO != null){
                session.setAttribute("userDTO", userDTO);
                session.setAttribute("fullname", userDTO.getFullname());
                response.sendRedirect("http://localhost:8080/DistroLab1_war_exploded/index.jsp");
            }else{
                String errorMessage = "Invalid username or password";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("http://localhost:8080/DistroLab1_war_exploded/login.jsp").forward(request, response);
            }
        }else if (pathInfo.equals("/logout")) {
            // Stäng av sessionen och logga ut användaren
            session.invalidate();
            response.sendRedirect("http://localhost:8080/DistroLab1_war_exploded/index.jsp"); // Du kan omdirigera användaren till inloggningssidan eller någon annan sida efter utloggningen.
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("Name");
        String pass = request.getParameter("Password");
        if (name != null && pass != null){
            session.setAttribute("Name", name);
            session.setAttribute("Password", pass);
            response.sendRedirect("index.jsp");
        }

    }
}