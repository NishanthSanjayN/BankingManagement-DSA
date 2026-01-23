package service;

public class FraudDetector {

    private static final double MAX_WITHDRAWAL = 50_000;

    public static boolean isFraudulent(double amount) {
        return amount > MAX_WITHDRAWAL;
    }
}
