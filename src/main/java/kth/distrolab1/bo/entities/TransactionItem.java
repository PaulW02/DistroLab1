package kth.distrolab1.bo.entities;

public class TransactionItem {
    private int transactionItemId;
    private int transactionId;
    private int itemId;
    private int quantity;
    private double itemPrice;

    public TransactionItem(int transactionItemId, int transactionId, int itemId, int quantity, double itemPrice) {
        this.transactionItemId = transactionItemId;
        this.transactionId = transactionId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public int getTransactionItemId() {
        return transactionItemId;
    }

    public void setTransactionItemId(int transactionItemId) {
        this.transactionItemId = transactionItemId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
