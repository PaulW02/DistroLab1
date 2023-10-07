package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.ui.dtos.ItemDTO;

import java.util.List;

public interface ItemService {
    List<ItemDTO> searchItems(String item);

    ItemDTO createItem(String itemName, String desc, String category, double price, int quantity, byte[] imageData);
    ItemDTO editItem(int itemId,String itemName, String desc, String category, double price, int quantity, byte[] imageData);
    List<ItemDTO> getAllItems();

    ItemDTO getItemById(int itemId);
}
