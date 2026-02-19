import service.BankService;
import exception.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        BankService bank = new BankService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show Account");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Acc No: ");
                        String acc = sc.next();
                        System.out.print("Name: ");
                        String name = sc.next();
                        System.out.print("Initial Balance: ");
                        double bal = sc.nextDouble();
                        bank.createAccount(acc, name, bal);
                    }
                    case 2 -> {
                        System.out.print("Acc No: ");
                        String acc = sc.next();
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        bank.deposit(acc, amt);
                    }
                    case 3 -> {
                        System.out.print("Acc No: ");
                        String acc = sc.next();
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        bank.withdraw(acc, amt);
                    }
                    case 4 -> {
                        System.out.print("Acc No: ");
                        String acc = sc.next();
                        bank.showAccount(acc);
                    }
                    case 5 -> System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
