package kth.distrolab1.bo.controllers;


import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.bo.services.ItemService;
import kth.distrolab1.bo.services.ItemServiceImpl;
import kth.distrolab1.ui.servlets.UserServlet;
import kth.distrolab1.ui.dtos.ItemDTO;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ItemController {

    private ItemService itemService = new ItemServiceImpl();

    public List<ItemDTO> searchItems(String item){
        List<Item> items = itemService.searchItems(item);
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item currentItem: items) {
            itemDTOS.add(new ItemDTO(currentItem.getId(), currentItem.getItemName(), currentItem.getDesc(), currentItem.getCategory(), currentItem.getPrice(), currentItem.getQuantity(), currentItem.getImageData()));
        }
        return itemDTOS;
    }

    public ItemDTO createItem(String itemName, String desc, String category, double price, int quantity, byte[] imageData){
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        Item item;
        if (itemName != null && desc != null && price != -1 && quantity != -1){
            item = itemService.createItem(itemName, desc, category, price, quantity, imageData);
            if (item != null){
                return new ItemDTO(item.getId(), item.getItemName(), item.getDesc(), item.getCategory(), item.getPrice(), item.getQuantity(), base64Image.getBytes());
            }
        }
        return null;
    }
    public ItemDTO editItem(int itemId, String itemName, String desc, String category, double price, int quantity, byte[] imageData){
        String base64Image = null;
        if (imageData != null) {
            base64Image = Base64.getEncoder().encodeToString(imageData);
        }
        Item item;
        if (itemName != null && desc != null && price != -1 && quantity != -1){
            item = itemService.editItem(itemId, itemName, desc, category, price, quantity, imageData);
            if (item != null){
                return new ItemDTO(item.getId(), item.getItemName(), item.getDesc(), item.getCategory(), item.getPrice(), item.getQuantity(), item.getImageData());
            }
        }
        return null;
    }

    public List<ItemDTO> getAllItems() {
        List<Item> items = itemService.getAllItems();
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item: items) {
            itemDTOs.add(new ItemDTO(item.getId(), item.getItemName(), item.getDesc(), item.getCategory(), item.getPrice(), item.getQuantity(), item.getImageData()));
        }
        return itemDTOs;
    }

    public ItemDTO getItemById(int itemId) {
        Item item = itemService.getItemById(itemId);
        if (item != null) {
            return new ItemDTO(item.getId(), item.getItemName(), item.getDesc(), item.getCategory(), item.getPrice(), item.getQuantity(), item.getImageData());
        }
        return null;
    }
}
