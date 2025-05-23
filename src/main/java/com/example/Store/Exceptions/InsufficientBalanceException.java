package com.example.Store.Exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Balance too small to complete the purchase.");
    }
}

