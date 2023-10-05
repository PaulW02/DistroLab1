package kth.distrolab1.bo.services;

import kth.distrolab1.bo.entities.Transaction;
import kth.distrolab1.bo.entities.TransactionItem;
import kth.distrolab1.db.repositories.TransactionRepository;
import kth.distrolab1.db.repositories.TransactionRepositoryImpl;
import kth.distrolab1.ui.dtos.TransactionDTO;
import kth.distrolab1.ui.dtos.TransactionItemDTO;

import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    @Override
    public TransactionDTO createTransaction(int userId, double totalAmount, List<TransactionItemDTO> transactionItems) {
        // Create a new transaction entity and set its properties
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setAmount(totalAmount);

        // You can save the transaction to the database using your repository, e.g., transactionRepository.save(transaction);

        // If the transaction is successfully saved and has an auto-generated ID, you can set it in the DTO
        if (transaction.getTransactionId() > 0) {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setTransactionId(transaction.getTransactionId());
            transactionDTO.setUserId(transaction.getUserId());
            transactionDTO.setAmount(transaction.getAmount());

            // Create and set transaction items for the DTO (if needed)
            List<TransactionItemDTO> itemDTOs = new ArrayList<>();
            for (TransactionItemDTO item : transactionItems) {
                // Convert and set properties from the entity to the DTO
                TransactionItemDTO itemDTO = new TransactionItemDTO();
                itemDTO.setItemId(item.getItemId());
                itemDTO.setItemPrice(item.getItemPrice());
                itemDTO.setQuantity(item.getQuantity());
                // Add the item to the list
                itemDTOs.add(itemDTO);
            }
            transactionDTO.setItemsBought(itemDTOs);
            return transactionDTO;
        } else {
            // Transaction creation failed, return null or handle the error accordingly
            return null;
        }
    }


    @Override
    public TransactionDTO getTransactionById(int transactionId) {
        return null;
    }

    @Override
    public List<TransactionDTO> getTransactionsForUser(int userId) {
        return null;
    }

    @Override
    public TransactionDTO updateTransaction(int transactionId, TransactionDTO transaction) {
        return null;
    }

    @Override
    public void deleteTransaction(int transactionId) {

    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAllTransactions();
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        for (Transaction transaction: transactions) {
            List<TransactionItemDTO> transactionItemDTOS = new ArrayList<>();
            for (TransactionItem transactionItem: transaction.getItemsBought()) {
                transactionItemDTOS.add(new TransactionItemDTO(transactionItem.getTransactionItemId(), transactionItem.getTransactionId(), transactionItem.getItemId(), transactionItem.getQuantity(), transactionItem.getItemPrice()));
            }
            transactionDTOs.add(new TransactionDTO(transaction.getTransactionId(), transaction.getUserId(), transaction.getPurchaseDate(), transaction.getAmount(), transactionItemDTOS));
        }
        return transactionDTOs;
    }

}
