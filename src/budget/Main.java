package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double totalIncome = 0;
        double totalExpenses = 0;
        List<String> list = new ArrayList<>();

        outerLoop:
        while (true) {
            System.out.println("""
                    Choose your action:
                    1) Add income
                    2) Add purchase
                    3) Show list of purchases
                    4) Balance
                    0) Exit
                    """);

            String choice = sc.next();

            switch (choice) {
                case "1":
                    System.out.println("Enter income:");
                    double income = sc.nextDouble();
                    totalIncome += income;
                    System.out.println("Income was added!\n");
                    break;

                case "2":
                    System.out.println("Enter purchase name:");
                    sc.nextLine(); // consume leftover newline
                    String purchaseName = sc.nextLine();
                    System.out.println("Enter its price:");
                    double purchasePrice = sc.nextDouble();
                    totalExpenses += purchasePrice;
                    list.add(purchaseName + " $" + String.format("%.2f", purchasePrice));
                    System.out.println("Purchase was added!\n");
                    break;

                case "3":
                    if (list.isEmpty()) {
                        System.out.println("The purchase list is empty.\n");
                    } else {
                        for (String s : list) {
                            System.out.println(s);
                        }
                        System.out.println("Total sum: $" + String.format("%.2f", totalExpenses)+"\n");
                    }
                    break;

                case "4":
                    double balance = totalIncome - totalExpenses;
                    if (balance < 0) balance = 0;
                    System.out.println("Balance: $" + String.format("%.2f", balance)+"\n");
                    break;

                case "0":
                    break outerLoop;

                default:
                    System.out.println("Unknown option, try again.\n");
                    break;
            }
        }

        System.out.println("Bye!");
        sc.close();
    }
}
