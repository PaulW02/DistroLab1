package kth.distrolab1.ui.servlets;
import kth.distrolab1.bo.controllers.ItemController;
import kth.distrolab1.bo.controllers.ShoppingBagController;
import kth.distrolab1.ui.dtos.ItemDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
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
        Part filePart = request.getPart("itemImage");
        byte[] itemImage = null;
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileContent = filePart.getInputStream();
            itemImage = new byte[(int) filePart.getSize()];
            fileContent.read(itemImage);
        }
        if (pathInfo.equals("/add")) {
            int itemId = Integer.valueOf(request.getParameter("itemId"));
            String itemName = request.getParameter("itemName");
            String itemDesc = request.getParameter("itemDesc");
            String itemCategory = request.getParameter("itemCategory");
            double itemPrice = Double.valueOf(request.getParameter("itemPrice"));
            int itemQuantity = Integer.valueOf(request.getParameter("itemQuantity"));

            shoppingBagController.addToShoppingBag(session, new ItemDTO(itemId, itemName, itemDesc, itemCategory, itemPrice, itemQuantity, itemImage));

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
