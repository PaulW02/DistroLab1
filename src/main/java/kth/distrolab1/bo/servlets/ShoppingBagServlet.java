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
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

@WebServlet(name = "shopping_bag", value = "/shoppingbag/*")
public class ShoppingBagServlet extends HttpServlet{

    private ItemHandler itemHandler = new ItemHandler();
    public void init() {
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

        if (pathInfo.equals("/add")){
            List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");
            int itemId = Integer.valueOf(request.getParameter("itemId"));
            String itemName = request.getParameter("itemName");
            String itemDesc = request.getParameter("itemDesc");
            int itemPrice = Integer.valueOf(request.getParameter("itemPrice"));
            int itemQuantity = Integer.valueOf(request.getParameter("itemQuantity"));
            if (shoppingBag == null) {
                shoppingBag = new ArrayList<>();
            }

            shoppingBag.add(new ItemDTO(itemId, itemName, itemDesc, itemPrice, itemQuantity));
            session.setAttribute("shoppingBag", shoppingBag);
            // Get the current URL and remove the last part (the "/add" portion)
            String currentURL = request.getRequestURL().toString();
            String newURL = currentURL.substring(0, currentURL.lastIndexOf('/'));
            newURL = currentURL.substring(0, newURL.lastIndexOf('/'));

            response.sendRedirect(newURL);
        }else if(pathInfo.equals("/remove")){
            List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");
            int itemId = Integer.valueOf(request.getParameter("itemId"));

            // Hitta och ta bort den befintliga varan från shoppingbagen baserat på itemId
            ItemDTO itemToRemove = null;
            for (ItemDTO item : shoppingBag) {
                if (item.getId() == itemId) {
                    itemToRemove = item;
                    break; // Hittade matchande objekt, avsluta loopen
                }
            }

            if (itemToRemove != null) {
                shoppingBag.remove(itemToRemove);
                session.setAttribute("shoppingBag", shoppingBag);
            }

            String currentURL = request.getRequestURL().toString();
            String newURL = currentURL.substring(0, currentURL.lastIndexOf('/'));
            newURL = currentURL.substring(0, newURL.lastIndexOf('/'));
            response.sendRedirect(newURL);
        }
    }

}
