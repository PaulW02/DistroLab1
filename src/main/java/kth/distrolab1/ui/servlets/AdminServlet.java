package kth.distrolab1.ui.servlets;

import kth.distrolab1.bo.controllers.ItemController;
import kth.distrolab1.bo.controllers.UserController;
import kth.distrolab1.ui.dtos.ItemDTO;
import kth.distrolab1.ui.dtos.UserDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@WebServlet(name = "admin", value = "/admin/*")
@MultipartConfig
public class AdminServlet extends HttpServlet {

    private ItemController itemController = new ItemController();
    private UserController userController = new UserController();

    public void init() {
    }

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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();

        if (pathInfo == null){
            response.sendRedirect("http://localhost:8080/");
        }else if (pathInfo.equals("/") && session.getAttribute("userDTO") != null && ((UserDTO) session.getAttribute("userDTO")).getRoles().contains("role_admin")){
            List<ItemDTO> items = itemController.getAllItems();
            List<UserDTO> users = userController.getAllUsers();
            if (items != null && users != null){
                request.setAttribute("items", items);
                request.setAttribute("users", users);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminPanel.jsp");
                requestDispatcher.forward(request, response);
            }else{
                String errorMessage = "Could not create item!";
                request.setAttribute("errorMessage", errorMessage);
                response.sendRedirect("http://localhost:8080/");

            }
        }else{
            response.sendRedirect("http://localhost:8080/");
        }
    }

    public void destroy() {
    }

}
