package kth.distrolab1.bo.servlets;

import kth.distrolab1.bo.handlers.ItemHandler;
import kth.distrolab1.ui.ItemDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "item", value = "/item/*")
public class ItemServlet extends HttpServlet {

    private ItemHandler itemHandler = new ItemHandler();
    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/create")){
            ItemDTO itemDTO = itemHandler.createItem(request.getParameter("itemName"), request.getParameter("desc"), Integer.valueOf(request.getParameter("price")), Integer.valueOf(request.getParameter("quantity")));
            if (itemDTO != null){
                response.sendRedirect("http://localhost:8080/DistroLab1_war_exploded/adminPanel.jsp");
            }else{
                String errorMessage = "Could not create item!";
                request.setAttribute("errorMessage", errorMessage);
                //response.sendRedirect("http://localhost:8080/DistroLab1_war_exploded/login.jsp");
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/all")){
            List<ItemDTO> items = itemHandler.getAllItems();
            if (items != null){
                request.setAttribute("items", items);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("../../../webapp/index.jsp");
                requestDispatcher.forward(request, response);
                //response.sendRedirect("http://localhost:8080/DistroLab1_war_exploded/index.jsp");
            }else{
                String errorMessage = "Could not create item!";
                request.setAttribute("errorMessage", errorMessage);
                //response.sendRedirect("http://localhost:8080/DistroLab1_war_exploded/login.jsp");
            }
        }else if (pathInfo != null) {
            // Extract the product ID using a regular expression
            // This regex captures a sequence of one or more digits
            String id = pathInfo.replaceAll(".*/(\\d+)", "$1");

            // Check if id is a valid integer (you can add more validation as needed)
            if (id.matches("\\d+")) {
                int itemId = Integer.parseInt(id);

                // Use itemId to retrieve the specific item
                ItemDTO item = itemHandler.getItemById(itemId);
                if (item != null) {
                    request.setAttribute("item", item);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/item.jsp");
                    dispatcher.forward(request, response);
                } else {
                    String errorMessage = "Could not find the specified item!";
                    request.setAttribute("errorMessage", errorMessage);
                    // Handle the error or redirect as needed
                    response.sendRedirect("http://localhost:8080/DistroLab1_war_exploded/");

                }
            } else {
                // Handle the case where the ID is not a valid integer
                String errorMessage = "Invalid product ID!";
                request.setAttribute("errorMessage", errorMessage);
                // Handle the error or redirect as needed
                response.sendRedirect("http://localhost:8080/DistroLab1_war_exploded/");
            }
        }
    }

    public void destroy() {
    }

}
