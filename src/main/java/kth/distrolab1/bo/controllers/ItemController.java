package kth.distrolab1.bo.controllers;


import kth.distrolab1.bo.services.ItemService;
import kth.distrolab1.bo.services.ItemServiceImpl;
import kth.distrolab1.ui.dtos.ItemDTO;

import java.util.List;

public class ItemController {

    private ItemService itemService = new ItemServiceImpl();

    public List<ItemDTO> searchItems(String item){
        return itemService.searchItems(item);
    }

    public ItemDTO createItem(String itemName, String desc, String category, double price, int quantity, byte[] imageData){
        if (itemName != null && desc != null && price != -1 && quantity != -1){
            return itemService.createItem(itemName, desc, category, price, quantity, imageData);
        }
        return null;
    }
    public ItemDTO editItem(int itemId, String itemName, String desc, String category, double price, int quantity, byte[] imageData){
        if (itemName != null && desc != null && price != -1 && quantity != -1){
            return itemService.editItem(itemId, itemName, desc, category, price, quantity, imageData);
        }
        return null;
    }

    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    public ItemDTO getItemById(int itemId) {
        return itemService.getItemById(itemId);
    }
}
