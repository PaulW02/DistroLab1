package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.bo.entities.Transaction;

import java.util.List;
public interface TransactionService {
    // Create a new transaction
    Transaction createTransaction(int userId, List<Item> items, double totalAmount);

    // Retrieve a transaction by its ID
    Transaction getTransactionById(int transactionId);

    // Retrieve all transactions for a specific user
    List<Transaction> getTransactionsForUser(int userId);

    // Update an existing transaction
    Transaction updateTransaction(Transaction transaction);

    // Delete a transaction by its ID
    void deleteTransaction(int transactionId);

    // Retrieve all transactions (for admin or reporting purposes)
    List<Transaction> getAllTransactions();
}

