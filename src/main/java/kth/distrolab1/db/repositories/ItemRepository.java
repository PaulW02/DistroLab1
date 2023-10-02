package kth.distrolab1.db.repositories;

import kth.distrolab1.bo.entities.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> searchItems(String item);
}
