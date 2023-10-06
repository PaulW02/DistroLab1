package kth.distrolab1.bo.entities;

public class Item {
    private int id;
    private String itemName;
    private String desc;
    private String category;
    private double price;
    private int quantity;
    private byte[] imageData;

    public Item(int id, String itemName, String desc, String category, double price, int quantity, byte[] imageData) {
        this.id = id;
        this.itemName = itemName;
        this.desc = desc;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.imageData = imageData;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
