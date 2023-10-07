package kth.distrolab1.ui.dtos;

public class ItemDTO {

    private int id;
    private String itemName;
    private String desc;
    private double price;
    private int quantity;

    private String category;
    private String imageData;

    public ItemDTO(int id, String itemName, String desc, String category, double price, int quantity, String imageData) {
        this.id = id;
        this.itemName = itemName;
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
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

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

}
