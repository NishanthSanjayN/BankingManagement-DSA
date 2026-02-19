package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final String timestamp;
    private final String type;
    private final double amount;
    private final double balanceAfter;

    // Constructor for NEW transactions
    public Transaction(String type, double amount, double balanceAfter) {
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    // Constructor for LOADED transactions
    public Transaction(String timestamp, String type, double amount, double balanceAfter) {
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public String serialize(String accNo) {
        return accNo + "," + timestamp + "," + type + "," + amount + "," + balanceAfter;
    }

    public static Transaction deserialize(String[] data) {
        return new Transaction(
                data[1], // timestamp
                data[2], // type
                Double.parseDouble(data[3]), // amount
                Double.parseDouble(data[4])  // balanceAfter
        );
    }

    @Override
    public String toString() {
        return String.format("%-20s %-12s %-10.2f %-10.2f",
                timestamp, type, amount, balanceAfter);
    }
}
