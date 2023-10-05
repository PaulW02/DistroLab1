package kth.distrolab1.bo.controllers;


import kth.distrolab1.bo.services.TransactionService;
import kth.distrolab1.bo.services.TransactionServiceImpl;
import kth.distrolab1.ui.dtos.TransactionDTO;
import kth.distrolab1.ui.dtos.TransactionItemDTO;

import java.util.List;

public class TransactionController {

    private TransactionService transactionService = new TransactionServiceImpl();

    public List<TransactionDTO> viewAllTransactions() {
        // Retrieve a list of all transactions and pass it to the view
        return transactionService.getAllTransactions();
    }

    public TransactionDTO viewTransaction(int id) {
        // Retrieve a specific transaction by ID and pass it to the view
        TransactionDTO transactionDTO = transactionService.getTransactionById(id);
        // Return the view name (e.g., transaction-details.jsp)
        return transactionDTO;
    }

    public TransactionDTO createTransaction(int userId, double amount, List<TransactionItemDTO> transactionItemDTOS) {
        return transactionService.createTransaction(userId, amount, transactionItemDTOS);
    }
    /*
    public String showEditForm(int id) {
        // Retrieve the transaction to be edited and display its data in an edit form
        TransactionDTO transaction = transactionService.getTransactionById(id);
        // Return a view for editing the transaction
        return "edit-transaction";
    }


    public String updateTransaction(int id, TransactionDTO updatedTransaction) {
        // Update the transaction with the provided data
        transactionService.updateTransaction(id, updatedTransaction);
        // Redirect to the list of all transactions or a success page
        return "redirect:/transactions/";
    }
*/

    public String deleteTransaction(int id) {
        // Delete the transaction with the specified ID
        transactionService.deleteTransaction(id);
        // Redirect to the list of all transactions or a success page
        return "redirect:/transactions/";
    }
}

