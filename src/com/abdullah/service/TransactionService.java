package com.abdullah.service;

import com.abdullah.model.Category;
import com.abdullah.model.Transaction;
import com.abdullah.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void addTransaction(double amount, LocalDate date, Category category, String description) {
        Transaction t = new Transaction(amount, date, category, description);
        repository.add(t);
    }

    public List<Transaction> getAllTransactions() {
        return repository.getAll();
    }

    public List<Transaction> getAllTransactionsOfRange(LocalDate start,LocalDate end) {
        return getAllTransactions().stream()
                .filter(t -> isWithinRange(t,start,end))
                .toList();
    }


    public void deleteTransaction(UUID id) {
        repository.delete(id);
    }

    public double calculateTotalTransactionsOfRange() {
        return repository.getAll()
                .stream()
                .mapToDouble(Transaction::getAmount).sum();
    }
    public double calculateTotalTransactionsOfRange(LocalDate start, LocalDate end) {
        return repository.getAll()
                .stream()
                .filter(t -> isWithinRange(t,start,end))
                .mapToDouble(Transaction::getAmount).sum();
    }


    public List<Transaction> getTransactionsByCategory(Category category) {
        return repository.getAll()
                .stream()
                .filter(t -> t.getCategory()
                .equals(category))
                .toList();
    }
    public List<Transaction> getTransactionsByCategoryOfRange(Category category, LocalDate start, LocalDate end) {
        return repository.getAll()
                .stream()
                .filter(t-> isWithinRange(t,start,end))
                .filter(t -> t.getCategory()
                .equals(category))
                .toList();
    }
    private boolean isWithinRange(Transaction t, LocalDate start, LocalDate end) {
        return !t.getDate().isBefore(start) && !t.getDate().isAfter(end);
    }
}
