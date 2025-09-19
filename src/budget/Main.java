package budget;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double balance = 0;
        double foodTotal = 0, clothesTotal = 0, entertainmentTotal = 0, otherTotal = 0;

        List<String> food = new ArrayList<>();
        List<String> clothes = new ArrayList<>();
        List<String> entertainment = new ArrayList<>();
        List<String> other = new ArrayList<>();
        List<String> all = new ArrayList<>();


        outerLoop:
        while (true) {
            System.out.println("""
                    Choose your action:
                    1) Add income
                    2) Add purchase
                    3) Show list of purchases
                    4) Balance
                    5) Save
                    6) Load
                    0) Exit
                    """);

            String choice = sc.next();

            switch (choice) {
                case "1":
                    System.out.println("Enter income:");
                    double income = sc.nextDouble();
                    balance += income;
                    System.out.println("Income was added!");
                    System.out.println();
                    break;

                case "2":
                    purchaseLoop:
                    while (true) {
                        System.out.println("""
                                Choose the type of purchase
                                1) Food
                                2) Clothes
                                3) Entertainment
                                4) Other
                                5) Back
                                """);

                        String type = sc.next();
                        sc.nextLine(); // clear newline

                        switch (type) {
                            case "1":
                                System.out.println("Enter purchase name:");
                                String foodName = sc.nextLine().trim();
                                System.out.println("Enter purchase price:");
                                double foodPrice = Double.parseDouble(sc.nextLine().trim());

                                String foodEntry = foodName + " $" + String.format("%.2f", foodPrice);
                                food.add(foodEntry);
                                all.add(foodEntry);
                                foodTotal += foodPrice;

                                System.out.println("Purchase was added!");
                                System.out.println();
                                break;

                            case "2":
                                System.out.println("Enter purchase name:");
                                String clothesName = sc.nextLine().trim();
                                System.out.println("Enter purchase price:");
                                double clothesPrice = Double.parseDouble(sc.nextLine().trim());

                                String clothesEntry = clothesName + " $" + String.format("%.2f", clothesPrice);
                                clothes.add(clothesEntry);
                                all.add(clothesEntry);
                                clothesTotal += clothesPrice;

                                System.out.println("Purchase was added!");
                                System.out.println();
                                break;

                            case "3":
                                System.out.println("Enter purchase name:");
                                String entertainmentName = sc.nextLine().trim();
                                System.out.println("Enter purchase price:");
                                double entertainmentPrice = Double.parseDouble(sc.nextLine().trim());

                                String entertainmentEntry = entertainmentName + " $" + String.format("%.2f", entertainmentPrice);
                                entertainment.add(entertainmentEntry);
                                all.add(entertainmentEntry);
                                entertainmentTotal += entertainmentPrice;

                                System.out.println("Purchase was added!");
                                System.out.println();
                                break;

                            case "4":
                                System.out.println("Enter purchase name:");
                                String otherName = sc.nextLine().trim();
                                System.out.println("Enter purchase price:");
                                double otherPrice = Double.parseDouble(sc.nextLine().trim());

                                String otherEntry = otherName + " $" + String.format("%.2f", otherPrice);
                                other.add(otherEntry);
                                all.add(otherEntry);
                                otherTotal += otherPrice;

                                System.out.println("Purchase was added!");
                                System.out.println();
                                break;

                            case "5":
                                break purchaseLoop;
                        }
                    }
                    break;

                case "3":
                    while (true) {
                        System.out.println("""
                                Choose the type of purchase
                                1) Food
                                2) Clothes
                                3) Entertainment
                                4) Other
                                5) All
                                6) Back
                                """);

                        String type = sc.next();

                        switch (type) {
                            case "1":
                                System.out.println("Food:");
                                if (food.isEmpty()) {
                                    System.out.println("The purchase list is empty!");
                                } else {
                                    for (String item : food) System.out.println(item);
                                    System.out.println("Total sum: $" + String.format("%.2f", foodTotal));
                                }
                                System.out.println();
                                break;

                            case "2":
                                System.out.println("Clothes:");
                                if (clothes.isEmpty()) {
                                    System.out.println("The purchase list is empty!");
                                } else {
                                    for (String item : clothes) System.out.println(item);
                                    System.out.println("Total sum: $" + String.format("%.2f", clothesTotal));
                                }
                                System.out.println();
                                break;

                            case "3":
                                System.out.println("Entertainment:");
                                if (entertainment.isEmpty()) {
                                    System.out.println("The purchase list is empty!");
                                } else {
                                    for (String item : entertainment) System.out.println(item);
                                    System.out.println("Total sum: $" + String.format("%.2f", entertainmentTotal));
                                }
                                System.out.println();
                                break;

                            case "4":
                                System.out.println("Other:");
                                if (other.isEmpty()) {
                                    System.out.println("The purchase list is empty!");
                                } else {
                                    for (String item : other) System.out.println(item);
                                    System.out.println("Total sum: $" + String.format("%.2f", otherTotal));
                                }
                                System.out.println();
                                break;

                            case "5":
                                System.out.println("All:");
                                if (all.isEmpty()) {
                                    System.out.println("The purchase list is empty!");
                                } else {
                                    for (String item : all) System.out.println(item);
                                    double total = foodTotal + clothesTotal + entertainmentTotal + otherTotal;
                                    System.out.println("Total sum: $" + String.format("%.2f", total));
                                }
                                System.out.println();
                                break;

                            case "6":
                                break;
                        }

                        if (type.equals("6")) break;
                    }
                    break;

                case "4":
                    double spent = foodTotal + clothesTotal + entertainmentTotal + otherTotal;
                    System.out.println("Balance: $" + String.format("%.2f", balance - spent));
                    System.out.println();
                    break;

                case "5":
                    try (PrintWriter writer = new PrintWriter(new FileWriter("purchases.txt"))) {
                        writer.println(balance); // save balance first
                        for (String item : all) {
                            String category = "";
                            if (food.contains(item)) category = "Food";
                            else if (clothes.contains(item)) category = "Clothes";
                            else if (entertainment.contains(item)) category = "Entertainment";
                            else if (other.contains(item)) category = "Other";
                            writer.println(category + ";" + item);
                        }
                        System.out.println("Purchases were saved!");
                        System.out.println();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    break;


                case "6":
                    try (BufferedReader reader = new BufferedReader(new FileReader("purchases.txt"))) {
                        all.clear();
                        food.clear();
                        clothes.clear();
                        entertainment.clear();
                        other.clear();
                        foodTotal = clothesTotal = entertainmentTotal = otherTotal = 0;

                        String line = reader.readLine();
                        if (line != null) {
                            balance = Double.parseDouble(line); // restore balance
                        }

                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(";", 2); // category ; item
                            if (parts.length == 2) {
                                String category = parts[0];
                                String item = parts[1];

                                all.add(item);

                                double price = Double.parseDouble(item.substring(item.lastIndexOf('$') + 1));

                                switch (category) {
                                    case "Food":
                                        food.add(item);
                                        foodTotal += price;
                                        break;
                                    case "Clothes":
                                        clothes.add(item);
                                        clothesTotal += price;
                                        break;
                                    case "Entertainment":
                                        entertainment.add(item);
                                        entertainmentTotal += price;
                                        break;
                                    case "Other":
                                        other.add(item);
                                        otherTotal += price;
                                        break;
                                }
                            }
                        }

                        System.out.println("Purchases were loaded!");
                        System.out.println();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    break;

                case "0":
                    System.out.println("Bye!");
                    sc.close();
                    break outerLoop;

                default:
                    System.out.println("Unknown option, try again.");
                    System.out.println();
                    break;
            }
        }
    }
}
