package kth.distrolab1.bo.entities;

import java.util.Date;
import java.util.List;

public class Transaction {

    private int transactionId;
    private int userId;
    private Date purchaseDate;
    private double amount;
    private List<TransactionItem> itemsBought;

    public Transaction(int transactionId, int userId, Date purchaseDate, double amount, List<TransactionItem> itemsBought) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
        this.itemsBought = itemsBought;
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

    public List<TransactionItem> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<TransactionItem> itemsBought) {
        this.itemsBought = itemsBought;
    }
}
