package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.db.repositories.ItemRepository;
import kth.distrolab1.db.repositories.ItemRepositoryImpl;
import kth.distrolab1.ui.dtos.ItemDTO;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Implementation of the ItemService interface, providing methods to manage and process items.
 * This class interacts with the ItemRepository to perform CRUD operations on items.
 */
public class ItemServiceImpl implements ItemService{
    private ItemRepository itemRepository = new ItemRepositoryImpl();

    /**
     * Searches for items based on the provided item name or part of the name.
     *
     * @param item The name or part of the name of the item to search for.
     * @return A list of ItemDTO that matches the search string.
     */
    @Override
    public List<ItemDTO> searchItems(String item) {
        List<Item> items = itemRepository.searchItems(item);
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item currentItem: items) {
            itemDTOS.add(new ItemDTO(currentItem.getId(), currentItem.getItemName(), currentItem.getDesc(), currentItem.getCategory(), currentItem.getPrice(), currentItem.getQuantity(), Base64.getEncoder().encodeToString(currentItem.getImageData())));
        }
        return itemDTOS;
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
    @Override
    public ItemDTO createItem(String itemName, String desc, String category, double price, int quantity, byte[] imageData) {
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        Item item;
        if (itemName != null && desc != null && price != -1 && quantity != -1){
            item = itemRepository.createItem(itemName, desc, category, price, quantity, imageData);
            if (item != null){
                return new ItemDTO(item.getId(), item.getItemName(), item.getDesc(), item.getCategory(), item.getPrice(), item.getQuantity(), base64Image);
            }
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
    @Override
    public ItemDTO editItem(int itemId, String itemName, String desc, String category, double price, int quantity, byte[] imageData) {
        String base64Image = null;
        if (imageData != null) {
            base64Image = Base64.getEncoder().encodeToString(imageData);
        }
        Item item;
        if (itemName != null && desc != null && price != -1 && quantity != -1){
            item = itemRepository.editItem(itemId,itemName, desc, category, price, quantity, imageData);
            if (item != null){
                return new ItemDTO(item.getId(), item.getItemName(), item.getDesc(), item.getCategory(), item.getPrice(), item.getQuantity(), base64Image);
            }
        }
        return null;
    }

    /**
     * Retrieves all items that have a quantity greater than zero.
     *
     * @return A list of all available ItemDTO.
     */
    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAllItems();
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item: items) {
            if (item.getQuantity() > 0) {
                itemDTOs.add(new ItemDTO(item.getId(), item.getItemName(), item.getDesc(), item.getCategory(), item.getPrice(), item.getQuantity(), Base64.getEncoder().encodeToString(item.getImageData())));
            }
        }
        return itemDTOs;
    }

    /**
     * Retrieves an item based on its ID.
     *
     * @param itemId The ID of the item to retrieve.
     * @return The ItemDTO corresponding to the provided ID or null if not found.
     */
    @Override
    public ItemDTO getItemById(int itemId) {
        Item item = itemRepository.findItemById(itemId);
        if (item != null) {
            return new ItemDTO(item.getId(), item.getItemName(), item.getDesc(), item.getCategory(), item.getPrice(), item.getQuantity(), Base64.getEncoder().encodeToString(item.getImageData()));
        }
        return null;
    }
}
