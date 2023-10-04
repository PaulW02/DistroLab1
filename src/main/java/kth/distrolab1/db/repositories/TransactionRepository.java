package kth.distrolab1.db.repositories;

import kth.distrolab1.bo.entities.Transaction;

import java.util.List;

public interface TransactionRepository {
    // Create a new transaction
    Transaction createTransaction(Transaction transaction);

    // Retrieve a transaction by its ID
    Transaction findTransactionById(int transactionId);

    // Retrieve all transactions for a specific user
    List<Transaction> findTransactionsForUser(int userId);

    // Update an existing transaction
    Transaction updateTransaction(Transaction transaction);

    // Delete a transaction by its ID
    void deleteTransaction(int transactionId);

    // Retrieve all transactions (for admin or reporting purposes)
    List<Transaction> findAllTransactions();
}
