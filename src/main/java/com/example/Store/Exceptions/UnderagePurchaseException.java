package com.example.Store.Exceptions;

public class UnderagePurchaseException extends RuntimeException {
    public UnderagePurchaseException(int requiredAge) {
        super("Customer is underage. Minimum age required is " + requiredAge + ".");
    }
}
