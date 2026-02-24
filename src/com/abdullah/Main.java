package com.abdullah;

import com.abdullah.repository.FileTransactionRepository;
import com.abdullah.repository.InMemoryTransactionRepository;
import com.abdullah.repository.TransactionRepository;
import com.abdullah.service.TransactionService;
import com.abdullah.ui.ConsoleUi;
import com.abdullah.ui.command.*;

import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        TransactionRepository repository = new FileTransactionRepository();
        TransactionService service = new TransactionService(repository);
        ConsoleInputReader inputReader = new ConsoleInputReader();
        Map<Integer, Command> commands = new HashMap<>();
        ConsoleUi ui = new ConsoleUi(inputReader,commands);
        commands.put( 1, new AddTransactionCommand(service,inputReader));
        commands.put( 2, new ViewAllTransactionsCommand(service,inputReader));
        commands.put( 3, new ViewTransactionsByCategoryCommand(service,inputReader));
        commands.put( 4, new CalculateTotalTransactionsCommand(service,inputReader));
        commands.put( 5 , new DeleteTransactionCommand(service,inputReader));
        ui.start();
    }
}
