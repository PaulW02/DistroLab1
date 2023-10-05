package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Transaction;
import kth.distrolab1.ui.dtos.TransactionDTO;
import kth.distrolab1.ui.dtos.TransactionItemDTO;

import java.util.List;
public interface TransactionService {
    // Create a new transaction
    TransactionDTO createTransaction(int userId, double items, List<TransactionItemDTO> totalAmount);

    // Retrieve a transaction by its ID
    TransactionDTO getTransactionById(int transactionId);

    // Retrieve all transactions for a specific user
    List<TransactionDTO> getTransactionsForUser(int userId);

    // Update an existing transaction

    TransactionDTO updateTransaction(int transactionId, TransactionDTO transaction);

    // Delete a transaction by its ID
    void deleteTransaction(int transactionId);

    // Retrieve all transactions (for admin or reporting purposes)
    List<TransactionDTO> getAllTransactions();

}

