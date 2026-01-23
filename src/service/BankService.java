package service;

import model.Account;
import util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BankService {

    private final Map<String, Account> accounts;

    public BankService() throws IOException {
        accounts = FileUtil.loadAccounts();
    }

    public void createAccount(String accNo, String name, double balance) throws IOException {
        accounts.put(accNo, new Account(accNo, name, balance));
        persist();
    }

    public void deposit(String accNo, double amt) throws IOException {
        accounts.get(accNo).deposit(amt);
        persist();
    }

    public void withdraw(String accNo, double amt) throws IOException {
        if (FraudDetector.isFraudulent(amt)) {
            System.out.println("⚠️ Fraud Alert: Withdrawal exceeds limit");
            return;
        }
        accounts.get(accNo).withdraw(amt);
        persist();
    }

    public void showAccount(String accNo) {
        Account a = accounts.get(accNo);
        System.out.println(a.getHolderName() + " | Balance: " + a.getBalance());
        a.getTransactions().forEach(System.out::println);
    }

    private void persist() throws IOException {
        FileUtil.saveAccounts(accounts);
        FileUtil.saveTransactions(accounts);
    }
}
