package com.abdullah.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.abdullah.exception.TransactionNotFoundException;
import com.abdullah.model.Transaction;

public class InMemoryTransactionRepository implements TransactionRepository {
    private final List<Transaction> history = new ArrayList<>();

    @Override
    public void add(Transaction t) {
        history.add(t);
    }

    @Override
    public List<Transaction> getAll() {
        return List.copyOf(history);
    }

    @Override
    public void delete(UUID id) throws TransactionNotFoundException {
        boolean isRemoved = history.removeIf(el -> el.getId().equals(id));
        if (!isRemoved)
            throw new TransactionNotFoundException();
    }

}
