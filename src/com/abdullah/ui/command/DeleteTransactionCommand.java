package com.abdullah.ui.command;

import com.abdullah.exception.TransactionNotFoundException;
import com.abdullah.service.TransactionService;
import com.abdullah.ui.ConsoleInputReader;

import java.util.UUID;

public class DeleteTransactionCommand implements Command {
    private final TransactionService service;
    private final ConsoleInputReader inputReader;

    public DeleteTransactionCommand(TransactionService service, ConsoleInputReader inputReader) {
        this.service = service;
        this.inputReader = inputReader;
    }

    @Override
    public void execute() {
        if (service.getAllTransactions().isEmpty()) {
            System.out.println("No Transactions found");
            return;
        }
        try {
            UUID id = inputReader.getAndValidateUserId();
            service.deleteTransaction(id);
            System.out.println("Transaction deleted successfully");
        } catch (TransactionNotFoundException e) {
            System.out.println("Transaction Not Found");
        }
    }
}
