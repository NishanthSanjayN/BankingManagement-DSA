package model;

import exception.InsufficientFundsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    private final String accountNumber;
    private final String holderName;
    private double balance;
    private final List<Transaction> transactions;

    // Constructor for NEW account creation
    public Account(String accNo, String name, double initialBalance) {
        this(accNo, name, initialBalance, false);
        transactions.add(new Transaction("INIT", initialBalance, balance));
    }

    // Constructor for LOADING existing account
    public Account(String accNo, String name, double balance, boolean isLoaded) {
        this.accountNumber = accNo;
        this.holderName = name;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public synchronized void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Deposit must be positive");

        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount, balance));
    }

    public synchronized void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0)
            throw new IllegalArgumentException("Withdrawal must be positive");

        if (amount > balance)
            throw new InsufficientFundsException("Insufficient balance");

        balance -= amount;
        transactions.add(new Transaction("WITHDRAW", amount, balance));
    }

    public synchronized void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }

    public synchronized double getBalance() { return balance; }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public String serialize() {
        return accountNumber + "," + holderName + "," + balance;
    }
}
