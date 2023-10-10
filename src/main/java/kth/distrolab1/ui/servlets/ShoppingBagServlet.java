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
import java.util.Base64;
import java.util.List;

/**
 * Servlet responsible for handling shopping bag-related operations.
 * This servlet provides endpoints for adding items to the shopping bag, removing items from the shopping bag, and purchasing items.
 */
@MultipartConfig
@WebServlet(name = "shopping_bag", value = "/shoppingbag/*")
public class ShoppingBagServlet extends HttpServlet{

    private ShoppingBagController shoppingBagController;


    /**
     * Initializes the servlet and the associated ShoppingBagController.
     */
    public void init() {
        shoppingBagController = new ShoppingBagController();
    }

    /**
     * Handles GET requests to the shopping bag servlet.
     * This method retrieves the shopping bag from the session and forwards it to the index.jsp page.
     *
     * @param request  The HttpServletRequest object containing client request data.
     * @param response The HttpServletResponse object for sending responses to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");
        request.setAttribute("shoppingBag", shoppingBag);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to the shopping bag servlet.
     * This method processes adding items to the shopping bag, removing items from the shopping bag, and purchasing items based on the provided path info.
     *
     * @param request  The HttpServletRequest object containing client request data.
     * @param response The HttpServletResponse object for sending responses to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
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
            String itemImageData = request.getParameter("itemImageData");

            shoppingBagController.addToShoppingBag(session, new ItemDTO(itemId, itemName, itemDesc, itemCategory, itemPrice, itemQuantity, itemImageData));

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
