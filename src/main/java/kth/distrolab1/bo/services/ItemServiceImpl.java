package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.db.repositories.ItemRepository;
import kth.distrolab1.db.repositories.ItemRepositoryImpl;

import java.util.List;

public class ItemServiceImpl implements ItemService{
    private ItemRepository itemRepository = new ItemRepositoryImpl();
    @Override
    public List<Item> searchItems(String item) {
        return itemRepository.searchItems(item);
    }

    @Override
    public Item createItem(String itemName, String desc, int price, int quantity) {
        return itemRepository.createItem(itemName, desc, price, quantity);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAllItems();
    }

    @Override
    public Item getItemById(int itemId) {
        return itemRepository.findItemById(itemId);
    }
}
