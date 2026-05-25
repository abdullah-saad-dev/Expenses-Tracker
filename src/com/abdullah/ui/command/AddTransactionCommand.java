package com.abdullah.ui.command;

import com.abdullah.model.Category;
import com.abdullah.service.TransactionService;
import com.abdullah.ui.ConsoleInputReader;

import java.time.LocalDate;

public class AddTransactionCommand implements Command {
    private final TransactionService service;
    private final ConsoleInputReader inputReader;
    public AddTransactionCommand(TransactionService service, ConsoleInputReader inputReader){
        this.service=service;
        this.inputReader=inputReader;
    }
    @Override
    public void execute() {
        System.out.println("--- let's Add A Transaction ---");
        double amount = inputReader.getAndValidateAmount();
        LocalDate date = inputReader.getAndValidateDate();
        Category category = inputReader.getAndValidateCategory();
        String description = inputReader.getDescription();
        service.addTransaction(amount, date, category, description);
        System.out.println("Transaction added");
    }
}
