package com.abdullah.ui.command;

import com.abdullah.service.TransactionService;
import com.abdullah.ui.ConsoleInputReader;

import java.time.LocalDate;

public class CalculateTotalTransactionsCommand implements Command {
    private final TransactionService service;
    private final ConsoleInputReader inputReader;

    public CalculateTotalTransactionsCommand(TransactionService service, ConsoleInputReader inputReader) {
        this.service = service;
        this.inputReader = inputReader;
    }

    @Override
    public void execute() {

        if (service.getAllTransactions().isEmpty()) {
            System.out.println("No transactions to sum. Please add Transactions first");
            return;
        }
        LocalDate[] range = inputReader.getDateRange();
        if (range == null) {
            System.out.println("Total money spent " + service.calculateTotalTransactionsOfRange());
            return;
        }
        double moneySpentWithinRange=  service.calculateTotalTransactionsOfRange(range[0], range[1]);
        if(moneySpentWithinRange == 0)
            System.out.println("No money Spent within this range");
        else {
            System.out.println("Total money spent within this range " + moneySpentWithinRange);
        }
    }
}
