package kth.distrolab1.db.repositories;

import kth.distrolab1.bo.entities.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> searchItems(String itemName);
    Item createItem(String itemName, String description, String category, double price, int quantity, byte[] itemData);
    Item editItem(int itemId, String itemName, String description, String category, double price, int quantity, byte[] itemData);
    List<Item> findAllItems();
    Item findItemById(int id);
}
