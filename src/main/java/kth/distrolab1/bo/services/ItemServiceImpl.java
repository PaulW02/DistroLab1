package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.db.repositories.ItemRepository;
import kth.distrolab1.db.repositories.ItemRepositoryImpl;
import kth.distrolab1.ui.dtos.ItemDTO;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ItemServiceImpl implements ItemService{
    private ItemRepository itemRepository = new ItemRepositoryImpl();
    @Override
    public List<ItemDTO> searchItems(String item) {
        List<Item> items = itemRepository.searchItems(item);
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item currentItem: items) {
            itemDTOS.add(new ItemDTO(currentItem.getId(), currentItem.getItemName(), currentItem.getDesc(), currentItem.getCategory(), currentItem.getPrice(), currentItem.getQuantity(), Base64.getEncoder().encodeToString(currentItem.getImageData())));
        }
        return itemDTOS;
    }

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

    @Override
    public ItemDTO getItemById(int itemId) {
        Item item = itemRepository.findItemById(itemId);
        if (item != null) {
            return new ItemDTO(item.getId(), item.getItemName(), item.getDesc(), item.getCategory(), item.getPrice(), item.getQuantity(), Base64.getEncoder().encodeToString(item.getImageData()));
        }
        return null;
    }
}
