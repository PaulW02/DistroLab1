package kth.distrolab1.db.repositories;


import kth.distrolab1.bo.entities.Transaction;

import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository{

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction findTransactionById(int transactionId) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionsForUser(int userId) {
        return null;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public void deleteTransaction(int transactionId) {

    }

    @Override
    public List<Transaction> findAllTransactions() {
        return null;
    }
}
