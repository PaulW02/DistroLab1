package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Item;

import java.util.List;

public interface ItemService {
    List<Item> searchItems(String item);
}
