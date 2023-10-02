package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.db.repositories.ItemRepositoryImpl;

import java.util.List;

public class ItemServiceImpl implements ItemService{

    @Override
    public List<Item> searchItems(String item) {
        return ItemRepositoryImpl.searchItems(item);
    }
}
