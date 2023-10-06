package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Item;

import java.util.List;

public interface ItemService {
    List<Item> searchItems(String item);

    Item createItem(String itemName, String desc, String category, double price, int quantity, byte[] imagePath);
    Item editItem(int itemId,String itemName, String desc, String category, double price, int quantity, byte[] imagePath);
    List<Item> getAllItems();

    Item getItemById(int itemId);
}
