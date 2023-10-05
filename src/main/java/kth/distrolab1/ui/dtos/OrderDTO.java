package kth.distrolab1.ui.dtos;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private int orderId;
    private int userId;
    private Date purchaseDate;
    private double amount;
    private List<OrderItemDTO> itemsBought;

    public OrderDTO(int orderId, int userId, Date purchaseDate, double amount, List<OrderItemDTO> itemsBought) {
        this.orderId = orderId;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
        this.itemsBought = itemsBought;
    }

    public OrderDTO() {
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

    public List<OrderItemDTO> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<OrderItemDTO> itemsBought) {
        this.itemsBought = itemsBought;
    }
}
