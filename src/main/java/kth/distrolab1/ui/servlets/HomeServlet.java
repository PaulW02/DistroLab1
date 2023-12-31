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
import java.util.List;

/**
 * Servlet responsible for handling operations related to the home page.
 * This servlet provides endpoints for displaying items on the home page.
 */
@WebServlet(name = "home", value = {"", "/"})
public class HomeServlet extends HttpServlet {

    private ItemController itemController = new ItemController();
    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Handles GET requests to the home servlet.
     * This method is responsible for displaying items on the home page.
     *
     * @param request  The HttpServletRequest object containing client request data.
     * @param response The HttpServletResponse object for sending responses to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/")){
            List<ItemDTO> items = itemController.getAllItems();
            if (items != null){
                request.setAttribute("items", items);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }else{
                String errorMessage = "Could not create item!";
                request.setAttribute("errorMessage", errorMessage);
            }
        }
    }

    public void destroy() {
    }

}
