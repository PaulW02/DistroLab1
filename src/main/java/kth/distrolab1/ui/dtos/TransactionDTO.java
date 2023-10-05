package kth.distrolab1.ui.dtos;

import kth.distrolab1.ui.dtos.TransactionItemDTO;

import java.util.Date;
import java.util.List;

public class TransactionDTO {
    private int transactionId;
    private int userId;
    private Date purchaseDate;
    private double amount;
    private List<TransactionItemDTO> itemsBought;

    public TransactionDTO(int transactionId, int userId, Date purchaseDate, double amount, List<TransactionItemDTO> itemsBought) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
        this.itemsBought = itemsBought;
    }

    public TransactionDTO() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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

    public List<TransactionItemDTO> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<TransactionItemDTO> itemsBought) {
        this.itemsBought = itemsBought;
    }
}
