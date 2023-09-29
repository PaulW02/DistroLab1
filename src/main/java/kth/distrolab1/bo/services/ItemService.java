package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.db.repositories.ItemDB;

import java.util.Collection;
import java.util.List;

public class ItemService {

    public List<Item> searchItems(String item){
        return ItemDB.searchItems(item);
    }

}
