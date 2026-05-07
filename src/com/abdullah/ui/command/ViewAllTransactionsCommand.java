package com.abdullah.ui.command;

import com.abdullah.model.Transaction;
import com.abdullah.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

public class ViewAllTransactionsCommand implements Command {
    private final TransactionService service;
    private final ConsoleInputReader inputReader;

    public ViewAllTransactionsCommand(TransactionService service, ConsoleInputReader inputReader) {
        this.service = service;
        this.inputReader = inputReader;
    }

    @Override
    public void execute() {
        List<Transaction> list = this.service.getAllTransactions();
        if (list.isEmpty()) {
            System.out.println("No transactions found. Please add a transaction first.");
            return;
        }
        LocalDate[] range = inputReader.getDateRange();
        if (range != null) {
            list = this.service.getAllTransactionsOfRange(range[0], range[1]);
            if (list.isEmpty()) {
                System.out.println("No transaction found within this range");
                return;
            }
        }
        printList(list);
    }

}
