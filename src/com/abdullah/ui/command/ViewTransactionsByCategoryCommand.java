package com.abdullah.ui.command;

import com.abdullah.model.Category;
import com.abdullah.model.Transaction;
import com.abdullah.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

public class ViewTransactionsByCategoryCommand implements Command {
    private final TransactionService service;
    private final ConsoleInputReader inputReader;

    public ViewTransactionsByCategoryCommand(TransactionService service, ConsoleInputReader inputReader) {
        this.service = service;
        this.inputReader = inputReader;
    }

    @Override
    public void execute() {

        if (service.getAllTransactions().isEmpty()) {
            System.out.println("No transactions added. please add one first");
            return;
        }
        Category userChoice = inputReader.getAndValidateCategory();
        List<Transaction> list = service.getTransactionsByCategory(userChoice);
        if (list.isEmpty()) {
            System.out.println("No transactions found with the category " + userChoice + ". ");
            return;
        }
        LocalDate[] range = inputReader.getDateRange();
        if (range == null)
            printList(list);
        else
            printList(service.getTransactionsByCategoryOfRange(userChoice, range[0], range[1]));
    }
}
