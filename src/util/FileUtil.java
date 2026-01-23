package util;

import model.Account;
import model.Transaction;

import java.io.*;
import java.util.*;

public class FileUtil {

    public static void saveAccounts(Map<String, Account> accounts) throws IOException {
        try (PrintWriter pw = new PrintWriter("accounts.txt")) {
            for (Account acc : accounts.values())
                pw.println(acc.serialize());
        }
    }

    public static void saveTransactions(Map<String, Account> accounts) throws IOException {
        try (PrintWriter pw = new PrintWriter("transactions.txt")) {
            for (Account acc : accounts.values())
                for (Transaction t : acc.getTransactions())
                    pw.println(t.serialize(acc.getAccountNumber()));
        }
    }

    public static Map<String, Account> loadAccounts() throws IOException {
        Map<String, Account> map = new HashMap<>();
        File file = new File("accounts.txt");
        if (!file.exists()) return map;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                map.put(d[0], new Account(d[0], d[1], Double.parseDouble(d[2])));
            }
        }
        return map;
    }
}
