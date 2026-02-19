package util;

import model.Account;
import model.Transaction;

import java.io.*;
import java.util.*;

public class FileUtil {

    private static final String ACCOUNT_FILE = "accounts.txt";
    private static final String TRANSACTION_FILE = "transactions.txt";

    public static void saveAccounts(Map<String, Account> accounts) throws IOException {
        try (PrintWriter pw = new PrintWriter(ACCOUNT_FILE)) {
            for (Account acc : accounts.values())
                pw.println(acc.serialize());
        }
    }

    public static void saveTransactions(Map<String, Account> accounts) throws IOException {
        try (PrintWriter pw = new PrintWriter(TRANSACTION_FILE)) {
            for (Account acc : accounts.values())
                for (Transaction t : acc.getTransactions())
                    pw.println(t.serialize(acc.getAccountNumber()));
        }
    }

    public static Map<String, Account> loadAccounts() throws IOException {

        Map<String, Account> map = new HashMap<>();

        File file = new File(ACCOUNT_FILE);
        if (!file.exists()) return map;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] d = line.split(",");
                if (d.length != 3) continue;

                map.put(d[0],
                        new Account(
                                d[0],
                                d[1],
                                Double.parseDouble(d[2]),
                                true
                        )
                );
            }
        }

        loadTransactions(map);

        return map;
    }

    private static void loadTransactions(Map<String, Account> accounts) throws IOException {

        File file = new File(TRANSACTION_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] d = line.split(",");
                if (d.length != 5) continue;

                String accNo = d[0];

                if (accounts.containsKey(accNo)) {

                    // Skip INIT transactions to prevent duplication
                    if (!d[2].equals("INIT")) {

                        Transaction t = Transaction.deserialize(d);
                        accounts.get(accNo).addTransaction(t);
                    }
                }
            }
        }
    }
}
