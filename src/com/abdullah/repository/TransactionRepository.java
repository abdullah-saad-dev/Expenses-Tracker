package com.abdullah.repository;

import com.abdullah.exception.TransactionNotFoundException;
import com.abdullah.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {

     void add(Transaction t);
     List<Transaction> getAll();
     void delete(UUID id) throws TransactionNotFoundException;


}
