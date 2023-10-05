package kth.distrolab1.ui.servlets;
import kth.distrolab1.bo.controllers.ItemController;
import kth.distrolab1.bo.controllers.ShoppingBagController;
import kth.distrolab1.ui.dtos.ItemDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "shopping_bag", value = "/shoppingbag/*")
public class ShoppingBagServlet extends HttpServlet{

    private ShoppingBagController shoppingBagController;

    public void init() {
        shoppingBagController = new ShoppingBagController();
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

        if (pathInfo.equals("/add")) {
            int itemId = Integer.valueOf(request.getParameter("itemId"));
            String itemName = request.getParameter("itemName");
            String itemDesc = request.getParameter("itemDesc");
            String itemCategory = request.getParameter("itemCategory");
            int itemPrice = Integer.valueOf(request.getParameter("itemPrice"));
            int itemQuantity = Integer.valueOf(request.getParameter("itemQuantity"));

            shoppingBagController.addToShoppingBag(session, new ItemDTO(itemId, itemName, itemDesc, itemCategory, itemPrice, itemQuantity));

            response.sendRedirect(request.getHeader("Referer"));

        } else if (pathInfo.equals("/remove")) {
            int itemId = Integer.valueOf(request.getParameter("itemId"));
            shoppingBagController.removeFromShoppingBag(session, itemId);
            response.sendRedirect(request.getHeader("Referer"));
        }else if(pathInfo.equals("/purchase")){
            List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");
            session.setAttribute("shoppingBag", new ArrayList<>());

            response.sendRedirect(request.getHeader("Referer"));
        }
    }

}
