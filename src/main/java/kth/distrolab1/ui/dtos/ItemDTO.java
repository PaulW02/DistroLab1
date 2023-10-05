package kth.distrolab1.ui.dtos;

public class ItemDTO {

    private int id;
    private String itemName;
    private String desc;
    private int price;
    private int quantity;

    private String category;

    public ItemDTO(int id, String itemName, String desc, String category, int price, int quantity) {
        this.id = id;
        this.itemName = itemName;
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
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

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}
}
