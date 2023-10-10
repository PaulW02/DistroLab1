package kth.distrolab1.bo.controllers;


import kth.distrolab1.bo.services.ItemService;
import kth.distrolab1.bo.services.ItemServiceImpl;
import kth.distrolab1.ui.dtos.ItemDTO;

import java.util.List;

public class ItemController {

    private ItemService itemService = new ItemServiceImpl();

    /**
     * Searches for items based on the provided item name or part of the name.
     *
     * @param item The name or part of the name of the item to search for.
     * @return A list of ItemDTO that matches the search string.
     */
    public List<ItemDTO> searchItems(String item){
        return itemService.searchItems(item);
    }

    /**
     * Creates a new item with the provided details.
     *
     * @param itemName The name of the item.
     * @param desc The description of the item.
     * @param category The category of the item.
     * @param price The price of the item.
     * @param quantity The available quantity of the item.
     * @param imageData The image data for the item.
     * @return The created ItemDTO or null if creation failed.
     */
    public ItemDTO createItem(String itemName, String desc, String category, double price, int quantity, byte[] imageData){
        if (itemName != null && desc != null && price != -1 && quantity != -1){
            return itemService.createItem(itemName, desc, category, price, quantity, imageData);
        }
        return null;
    }

    /**
     * Edits an existing item with the provided details.
     *
     * @param itemId The ID of the item to edit.
     * @param itemName The new name of the item.
     * @param desc The new description of the item.
     * @param category The new category of the item.
     * @param price The new price of the item.
     * @param quantity The new available quantity of the item.
     * @param imageData The new image data for the item.
     * @return The edited ItemDTO or null if editing failed.
     */
    public ItemDTO editItem(int itemId, String itemName, String desc, String category, double price, int quantity, byte[] imageData){
        if (itemName != null && desc != null && price != -1 && quantity != -1){
            return itemService.editItem(itemId, itemName, desc, category, price, quantity, imageData);
        }
        return null;
    }

    /**
     * Retrieves all items.
     *
     * @return A list of all ItemDTO.
     */
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    /**
     * Retrieves an item based on its ID.
     *
     * @param itemId The ID of the item to retrieve.
     * @return The ItemDTO corresponding to the provided ID.
     */
    public ItemDTO getItemById(int itemId) {
        return itemService.getItemById(itemId);
    }
}
