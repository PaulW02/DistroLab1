package kth.distrolab1.bo.controllers;

import kth.distrolab1.ui.dtos.ItemDTO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBagController {
    public void addToShoppingBag(HttpSession session, ItemDTO itemDTO) {
        List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");
        if (shoppingBag == null) {
            shoppingBag = new ArrayList<>();
        }
        shoppingBag.add(itemDTO);
        session.setAttribute("shoppingBag", shoppingBag);
    }

    public void removeFromShoppingBag(HttpSession session, int itemId) {
        List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");
        // Hitta och ta bort den befintliga varan från shoppingbagen baserat på itemId
        ItemDTO itemToRemove = null;
        for (ItemDTO item : shoppingBag) {
            if (item.getId() == itemId) {
                itemToRemove = item;
                break; // Hittade matchande objekt, avsluta loopen
            }
        }
        if (itemToRemove != null) {
            shoppingBag.remove(itemToRemove);
            session.setAttribute("shoppingBag", shoppingBag);
        }
    }
}
