package kth.distrolab1.bo.servlets;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.bo.services.ItemServiceImpl;
import kth.distrolab1.ui.ItemDTO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "itemHandler", value = "/itemHandler")
public class ItemServlet extends UserServlet {

    private ItemServiceImpl itemService = new ItemServiceImpl();

    private String message;

    public void init() {
        message = "Hello World ITEMMM!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }

    public List<ItemDTO> searchItems(String item){
        List<Item> items = itemService.searchItems(item);
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item currentItem: items) {
            itemDTOS.add(new ItemDTO(currentItem.getName(), currentItem.getPrice(), currentItem.getStock()));
        }
        return itemDTOS;
    }

}
