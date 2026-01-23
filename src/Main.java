import service.BankService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        BankService bank = new BankService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Create 2.Deposit 3.Withdraw 4.View 0.Exit");
            int c = sc.nextInt();

            if (c == 0) {
                System.out.println("Exiting...");
                break;
            }

            String acc;
            double amt;

            switch (c) {
                case 1 -> {
                    System.out.print("Acc No: ");
                    acc = sc.next();
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Balance: ");
                    amt = sc.nextDouble();
                    bank.createAccount(acc, name, amt);
                }
                case 2 -> {
                    System.out.print("Acc No: ");
                    acc = sc.next();
                    System.out.print("Amount: ");
                    amt = sc.nextDouble();
                    bank.deposit(acc, amt);
                }
                case 3 -> {
                    System.out.print("Acc No: ");
                    acc = sc.next();
                    System.out.print("Amount: ");
                    amt = sc.nextDouble();
                    bank.withdraw(acc, amt);
                }
                case 4 -> {
                    System.out.print("Acc No: ");
                    acc = sc.next();
                    bank.showAccount(acc);
                }
                default -> System.out.println("Invalid choice");
            }
        }
        sc.close();
    }
}
