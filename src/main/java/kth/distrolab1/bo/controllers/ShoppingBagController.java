package kth.distrolab1.bo.controllers;

import kth.distrolab1.ui.dtos.ItemDTO;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
/**
 * The ShoppingBagController class provides methods to manage the shopping bag.
 * It allows items to be added to or removed from the shopping bag stored in the session.
 */
public class ShoppingBagController {

    /**
     * Adds an item to the shopping bag stored in the session.
     *
     * @param session The current HTTP session.
     * @param itemDTO The item to be added to the shopping bag.
     */
    public void addToShoppingBag(HttpSession session, ItemDTO itemDTO) {
        List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");
        if (shoppingBag == null) {
            shoppingBag = new ArrayList<>();
        }
        shoppingBag.add(itemDTO);
        session.setAttribute("shoppingBag", shoppingBag);
    }

    /**
     * Removes an item from the shopping bag based on its ID.
     *
     * @param session The current HTTP session.
     * @param itemId The ID of the item to be removed from the shopping bag.
     */
    public void removeFromShoppingBag(HttpSession session, int itemId) {
        List<ItemDTO> shoppingBag = (List<ItemDTO>) session.getAttribute("shoppingBag");
        ItemDTO itemToRemove = null;
        for (ItemDTO item : shoppingBag) {
            if (item.getId() == itemId) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            shoppingBag.remove(itemToRemove);
            session.setAttribute("shoppingBag", shoppingBag);
        }
    }
}