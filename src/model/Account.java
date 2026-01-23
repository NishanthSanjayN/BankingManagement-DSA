package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountNumber;
    private final String holderName;
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    public Account(String accNo, String name, double initialBalance) {
        this.accountNumber = accNo;
        this.holderName = name;
        this.balance = initialBalance;
        transactions.add(new Transaction("INIT", initialBalance, balance));
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount, balance));
    }

    public void withdraw(double amount) {
        balance -= amount;
        transactions.add(new Transaction("WITHDRAW", amount, balance));
    }

    public String serialize() {
        return accountNumber + "," + holderName + "," + balance;
    }
}
