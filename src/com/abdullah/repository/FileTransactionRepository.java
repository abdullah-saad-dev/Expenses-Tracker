package com.abdullah.repository;

import com.abdullah.exception.TransactionNotFoundException;
import com.abdullah.model.Category;
import com.abdullah.model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.util.UUID;

public class FileTransactionRepository extends InMemoryTransactionRepository {
    private final static String PATH_NAME = "transactions.txt";
    private final File file;

    public FileTransactionRepository() {
        file = new File(PATH_NAME);
        createNewFileIfNotExist();
        loadTransactions();
    }

    private void createNewFileIfNotExist() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Error : couldn't create transaction.txt files. Please check your permissions. ");
        }
    }

    private void loadTransactions() {
        try (Reader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] attributes = line.split(",");
                UUID id = UUID.fromString(attributes[0]);
                double amount = Double.parseDouble(attributes[1]);
                LocalDate date = LocalDate.parse(attributes[2]);
                Category category = Category.valueOf(attributes[3].toUpperCase());
                String description = attributes[4];
                super.add(new Transaction(id, amount, date, category, description));
            }
        } catch (IOException e) {
            System.err.println("An Error Occurred : " + e.getMessage());
        }
    }

    @Override
    public void add(Transaction t) {
        super.add(t);
        appendToFile(t);
    }

    public void appendToFile(Transaction t) {
        try (Writer writer = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(t.getId() + "," + t.getAmount() + "," + t.getDate() + ","
                    + t.getCategory().name() + "," + t.getDescription() + "\n");
        } catch (IOException e) {
            System.err.println("An Error Occurred" + e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) throws TransactionNotFoundException {
        super.delete(id);
        reWriteEntireFile();
    }

    public void reWriteEntireFile() {
        file.delete();
        createNewFileIfNotExist();
        for (Transaction t : super.getAll()) {
            this.appendToFile(t);
        }
    }
}
