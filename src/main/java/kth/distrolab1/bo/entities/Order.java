package kth.distrolab1.bo.entities;

import java.util.Date;
import java.util.List;

public class Order {

    private int orderId;
    private int userId;
    private Date purchaseDate;
    private double amount;
    private List<OrderItem> itemsBought;

    public Order(int orderId, int userId, Date purchaseDate, double amount, List<OrderItem> itemsBought) {
        this.orderId = orderId;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
        this.itemsBought = itemsBought;
    }

    public Order() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<OrderItem> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<OrderItem> itemsBought) {
        this.itemsBought = itemsBought;
    }
}
