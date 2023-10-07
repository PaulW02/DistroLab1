package kth.distrolab1.ui.servlets;

import kth.distrolab1.bo.controllers.ItemController;
import kth.distrolab1.ui.dtos.ItemDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


@WebServlet(name = "image", value = "/resources/*")
public class LogoServlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("image/png");

        InputStream imageStream = getServletContext().getResourceAsStream("/resources/Innovania-white.png");
        byte[] buffer = new byte[1024];
        int bytesRead;
        OutputStream out = response.getOutputStream();
        while ((bytesRead = imageStream.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        imageStream.close();
        out.close();
    }


    public void destroy() {
    }

}
