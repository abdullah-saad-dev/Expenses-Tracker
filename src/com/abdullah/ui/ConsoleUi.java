package com.abdullah.ui;

import com.abdullah.ui.command.*;

import java.util.Map;

public class ConsoleUi {

    private final ConsoleInputReader inputReader;
    private final Map<Integer, Command> commands;

    public ConsoleUi(ConsoleInputReader inputReader, Map<Integer, Command> commands) {
        this.inputReader = inputReader;
        this.commands = commands;
    }

    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            printMainMenu();
            int userChoice = inputReader.getUserChoice();
            if (userChoice == 6)
                isRunning = false;
            else {
                Command command = commands.get(userChoice);
               if(command!=null)
                   command.execute();
               else
                   System.out.println("Please enter a valid option");
                System.out.println("Please enter any key to continue");
                inputReader.getUserInput();
            }
        }
    }

    private void printMainMenu() {
        System.out.print("""
                --- Expense Tracker Menu ---
                1. Add a Transaction
                2. View All Transactions
                3. View Transactions by Category
                4. Calculate Total Expenses
                5. Delete a Transaction
                6. Exit
                Choose an option :\s""");
    }

}
