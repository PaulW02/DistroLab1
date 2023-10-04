package kth.distrolab1.bo.entities;

public class Item {
    private int id;
    private String itemName;
    private String desc;
    private int price;
    private int quantity;

    public Item(int id, String itemName, String desc, int price, int quantity) {
        this.id = id;
        this.itemName = itemName;
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String name) {
        this.itemName = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
