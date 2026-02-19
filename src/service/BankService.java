package service;

import exception.*;
import model.Account;
import util.FileUtil;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {

    private final Map<String, Account> accounts;

    public BankService() throws IOException {
        accounts = new ConcurrentHashMap<>(FileUtil.loadAccounts());
    }

    public void createAccount(String accNo, String name, double balance) throws IOException {
        if (accounts.containsKey(accNo))
            throw new IllegalArgumentException("Account already exists");

        accounts.put(accNo, new Account(accNo, name, balance));
        persist();
    }

    public void deposit(String accNo, double amt)
            throws AccountNotFoundException, IOException {

        Account acc = getAccount(accNo);
        acc.deposit(amt);
        persist();
    }

    public void withdraw(String accNo, double amt)
            throws AccountNotFoundException,
            InsufficientFundsException,
            FraudDetectedException,
            IOException {

        if (FraudDetector.isFraudulent(amt))
            throw new FraudDetectedException("Withdrawal exceeds fraud limit");

        Account acc = getAccount(accNo);
        acc.withdraw(amt);
        persist();
    }

    public void showAccount(String accNo) throws AccountNotFoundException {
        Account a = getAccount(accNo);

        System.out.println("Holder: " + a.getHolderName());
        System.out.println("Balance: " + a.getBalance());
        a.getTransactions().forEach(System.out::println);
    }

    private Account getAccount(String accNo) throws AccountNotFoundException {
        Account acc = accounts.get(accNo);
        if (acc == null)
            throw new AccountNotFoundException("Account not found");
        return acc;
    }

    private synchronized void persist() throws IOException {
        FileUtil.saveAccounts(accounts);
        FileUtil.saveTransactions(accounts);
    }
}
