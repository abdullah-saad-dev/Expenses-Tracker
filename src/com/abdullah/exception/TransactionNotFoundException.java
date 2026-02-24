package com.abdullah.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
    public TransactionNotFoundException(){}
}
