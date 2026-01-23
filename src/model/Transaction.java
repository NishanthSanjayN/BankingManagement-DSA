package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String timestamp;
    private final String type;
    private final double amount;
    private final double balanceAfter;

    public Transaction(String type, double amount, double balanceAfter) {
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public String serialize(String accNo) {
        return accNo + "," + timestamp + "," + type + "," + amount + "," + balanceAfter;
    }

    public static Transaction deserialize(String[] data) {
        return new Transaction(data[2],
                Double.parseDouble(data[3]),
                Double.parseDouble(data[4]));
    }

    @Override
    public String toString() {
        return String.format("%-20s %-12s %-10.2f %-10.2f",
                timestamp, type, amount, balanceAfter);
    }
}
