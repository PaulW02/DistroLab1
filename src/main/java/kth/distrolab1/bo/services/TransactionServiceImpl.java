package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Item;
import kth.distrolab1.bo.entities.Transaction;
import kth.distrolab1.bo.entities.User;
import kth.distrolab1.db.repositories.TransactionRepository;
import kth.distrolab1.db.repositories.TransactionRepositoryImpl;
import kth.distrolab1.db.repositories.UserRepository;
import kth.distrolab1.db.repositories.UserRepositoryImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    @Override
    public Transaction createTransaction(int userId, List<Item> items, double totalAmount) {
        return null;
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsForUser(int userId) {
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
    public List<Transaction> getAllTransactions() {
        return null;
    }
}
