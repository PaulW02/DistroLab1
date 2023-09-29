package kth.distrolab1.bo.handlers;

import kth.distrolab1.HttpServlet;
import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.bo.services.ItemService;
import kth.distrolab1.ui.ItemDTO;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/itemHandler")
public class ItemHandlerServlet extends HttpServlet {

    private ItemService itemService;

    public List<ItemDTO> searchItems(String item){
        List<Item> items = itemService.searchItems(item);
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item currentItem: items) {
            itemDTOS.add(new ItemDTO(currentItem.getName(), currentItem.getPrice(), currentItem.getStock()));
        }
        return itemDTOS;
    }

}
