package kth.distrolab1.ui.servlets;

import kth.distrolab1.bo.controllers.ItemController;
import kth.distrolab1.ui.dtos.ItemDTO;
import kth.distrolab1.ui.dtos.UserDTO;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Servlet responsible for handling operations related to items.
 * This servlet provides endpoints for creating, editing, and viewing items.
 */
@WebServlet(name = "item", value = "/item/*")
@MultipartConfig
public class ItemServlet extends HttpServlet {

    private ItemController itemController = new ItemController();
    public void init() {
    }

    /**
     * Handles POST requests to the item servlet.
     * This method is responsible for creating and editing items.
     *
     * @param request  The HttpServletRequest object containing client request data.
     * @param response The HttpServletResponse object for sending responses to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();

        if (pathInfo.equals("/create") && session.getAttribute("userDTO") != null && ((UserDTO) session.getAttribute("userDTO")).getRoles().contains("role_admin")){
            Part filePart = request.getPart("itemImage"); // Retrieves <input type="file" name="itemImage">
            InputStream fileContent = filePart.getInputStream();
            byte[] imageData = new byte[(int) filePart.getSize()];
            fileContent.read(imageData);
            ItemDTO itemDTO = itemController.createItem(request.getParameter("itemName"), request.getParameter("desc"), request.getParameter("category"), Double.valueOf(request.getParameter("price")), Integer.valueOf(request.getParameter("quantity")), imageData);
            if (itemDTO != null){
                response.sendRedirect(request.getHeader("Referer"));
            }else{
                String errorMessage = "Could not create item!";
                request.setAttribute("errorMessage", errorMessage);
            }
        }else if(pathInfo.equals("/edit") && session.getAttribute("userDTO") != null && ((UserDTO) session.getAttribute("userDTO")).getRoles().contains("role_admin")){
            int itemId = Integer.valueOf(request.getParameter("item")); // Get itemId from request
            ItemDTO itemDTO = itemController.getItemById(itemId);
            Part filePart = request.getPart("editItemImageData");
            byte[] imageData = null;
            if (filePart != null && filePart.getSize() > 0) {
                InputStream fileContent = filePart.getInputStream();
                imageData = new byte[(int) filePart.getSize()];
                fileContent.read(imageData);
            } else {
                imageData = itemDTO.getImageData().getBytes(); // Use the existing image data if no new file is uploaded
            }

            String itemName = request.getParameter("editItemName").length() > 0 ? request.getParameter("editItemName") : itemDTO.getItemName();
            String description = request.getParameter("editItemDescription").length() > 0 ? request.getParameter("editItemDescription") : itemDTO.getDesc();
            String category = request.getParameter("editItemCategory").length() > 0 ? request.getParameter("editItemCategory") : itemDTO.getCategory();
            double price = request.getParameter("editItemPrice").length() > 0 ? Double.parseDouble(request.getParameter("editItemPrice")) : itemDTO.getPrice();
            int quantity = request.getParameter("editItemQuantity").length() > 0 ? Integer.parseInt(request.getParameter("editItemQuantity")) : itemDTO.getQuantity();

            ItemDTO updatedItemDTO = itemController.editItem(itemId, itemName, description, category, price, quantity, imageData);
            if (updatedItemDTO != null){
                response.sendRedirect(request.getHeader("Referer"));
            } else {
                String errorMessage = "Could not create item!";
                request.setAttribute("errorMessage", errorMessage);
            }
        }else{
            response.sendRedirect("http://localhost:8080/");
        }

    }

    /**
     * Handles GET requests to the item servlet.
     * This method is responsible for displaying all items or a specific item based on the request path.
     *
     * @param request  The HttpServletRequest object containing client request data.
     * @param response The HttpServletResponse object for sending responses to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();

        if (pathInfo.equals("/all")){
            List<ItemDTO> items = itemController.getAllItems();
            if (items != null){
                request.setAttribute("items", items);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/items.jsp");
                requestDispatcher.forward(request, response);
            }else{
                String errorMessage = "Could not create item!";
                request.setAttribute("errorMessage", errorMessage);

            }
        }else if (pathInfo != null) {
            // Extract the product ID using a regular expression
            // This regex captures a sequence of one or more digits
            String id = pathInfo.replaceAll(".*/(\\d+)", "$1");

            // Check if id is a valid integer (you can add more validation as needed)
            if (id.matches("\\d+")) {
                int itemId = Integer.parseInt(id);

                // Use itemId to retrieve the specific item
                ItemDTO item = itemController.getItemById(itemId);
                if (item != null) {
                    request.setAttribute("item", item);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/item.jsp");
                    dispatcher.forward(request, response);
                } else {
                    String errorMessage = "Could not find the specified item!";
                    request.setAttribute("errorMessage", errorMessage);
                    // Handle the error or redirect as needed
                    response.sendRedirect("http://localhost:8080/");

                }
            } else {
                // Handle the case where the ID is not a valid integer
                String errorMessage = "Invalid product ID!";
                request.setAttribute("errorMessage", errorMessage);
                // Handle the error or redirect as needed
                response.sendRedirect("http://localhost:8080/");
            }
        }
    }

    public void destroy() {
    }

}
