package com.abdullah.ui;

import com.abdullah.model.Category;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleInputReader {
    private final Scanner scanner;

    public ConsoleInputReader() {
        this.scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public int getUserChoice() {
        int userChoice = 0;
        while (userChoice == 0) {
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid option");
            }
        }
        return userChoice;
    }

    public UUID getAndValidateUserId() {
        UUID id = null;
        while (id == null) {
            try {
                System.out.println("Please enter the transaction ID");
                id = UUID.fromString(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.err.println(" invalid ID ");
            }
        }
        return id;
    }

    public double getAndValidateAmount() {
        double amount = 0;
        while (amount <= 0) {
            System.out.print("Enter the spent amount : ");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0)
                    System.err.println("Invalid amount");
            } catch (NumberFormatException e) {
                System.err.println("Invalid format");
            }
        }
        return amount;
    }

    public LocalDate getAndValidateDate() {
        System.out.println("""
                Is it today?
                1.Yes
                2.No
                """);
        if (getUserChoice() == 1)
            return LocalDate.now();
        LocalDate date = null;
        while (date == null) {
            System.out.print("Enter the date ex: 2026-02-19 : ");
            try {
                date = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeException e) {
                System.err.println("Invalid Date, please use the format YYYY-MM-DD");
            }
        }
        return date;
    }

    public Category getAndValidateCategory() {
        Category category = null;
        Category[] categories = Category.values();
        String categoriesString = Arrays.toString(categories);
        while (category == null) {
            System.out.println("Choose one of these categories :\n "
                    + categoriesString + " : ");
            try {
                category = Category.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid category, please enter a valid category \n");
            }
        }
        return category;
    }

    public String getDescription() {
        System.out.print("Enter The Description : ");
        return scanner.nextLine().replace(",", "");
    }

    public LocalDate[] getDateRange() throws NullPointerException {
        System.out.print("""
                --- Select Date Range ---
                1. Today
                2. YesterDay
                3. Last 7 Days (Including Today)
                4. Last 30 Days (Including Today)
                5. This Calendar Month
                6. All Time
                7. Custom Date Range
                Choose an option :
                """);
        int input = 0;
        LocalDate[] range = null;
        while (input == 0) {
            input = getUserChoice();
            LocalDate today = LocalDate.now();
            // range ={ start, end}
            range = new LocalDate[2];
            range[1] = today;
            switch (input) {
                case 1:
                    range[0] = today;
                    break;
                case 2:
                    range[0] = today.minusDays(1);
                    range[1] = range[0];
                    break;
                case 3:
                    range[0] = today.minusDays(6);
                    break;
                case 4:
                    range[0] = today.minusDays(29);
                    break;
                case 5:
                    range[0] = today.withDayOfMonth(1);
                    break;
                case 6:
                    return null;
                case 7:
                    System.out.print("Please enter the starting date : ");
                    range[0] = getAndValidateDate();
                    range[1] = getAndValidateDate();
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    input = 0;
                    break;
            }
        }
        return range;
    }
}
