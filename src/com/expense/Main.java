package com.expense;

import com.expense.model.Expense;
import com.expense.service.ExpenseService;
import com.expense.storage.FileManager;
import com.expense.utils.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ExpenseService expenseService = new ExpenseService();
    private static final FileManager fileManager = new FileManager();

    public static void main(String[] args) {

        // Load previously saved expenses
        List<Expense> savedExpenses = fileManager.loadExpenses();

        for (Expense expense : savedExpenses) {
            expenseService.addExpense(expense);
        }

        boolean running = true;

        System.out.println("=================================");
        System.out.println("   STUDENT EXPENSE TRACKER");
        System.out.println("=================================");

        while (running) {

            showMenu();
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addExpense();
                    break;

                case "2":
                    expenseService.viewExpenses();
                    break;

                case "3":
                    showTotalExpense();
                    break;

                case "4":
                    deleteExpense();
                    break;

                case "5":
                    fileManager.saveExpenses(expenseService.getExpenses());
                    System.out.println("Thank you for using Student Expense Tracker!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Display menu
    private static void showMenu() {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. Add Expense");
        System.out.println("2. View All Expenses");
        System.out.println("3. View Total Expense");
        System.out.println("4. Delete Expense");
        System.out.println("5. Save and Exit");
        System.out.println("----------------------------");
    }

    // Add a new expense
    private static void addExpense() {

        try {
            System.out.print("Enter Expense ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (!InputValidator.isValidId(id)) {
                System.out.println("Invalid ID. ID must be greater than 0.");
                return;
            }

            System.out.print("Enter Category: ");
            String category = scanner.nextLine();

            if (!InputValidator.isValidText(category)) {
                System.out.println("Category cannot be empty.");
                return;
            }

            System.out.print("Enter Amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            if (!InputValidator.isValidAmount(amount)) {
                System.out.println("Amount must be greater than 0.");
                return;
            }

            System.out.print("Enter Date (DD-MM-YYYY): ");
            String date = scanner.nextLine();

            System.out.print("Enter Description: ");
            String description = scanner.nextLine();

            Expense expense = new Expense(
                    id,
                    category,
                    amount,
                    date,
                    description
            );

            expenseService.addExpense(expense);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    // Display total expense
    private static void showTotalExpense() {
        double total = expenseService.getTotalExpense();
        System.out.println("Total Expense: ₹" + total);
    }

    // Delete an expense
    private static void deleteExpense() {

        try {
            System.out.print("Enter Expense ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            expenseService.deleteExpense(id);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid ID.");
        }
    }
}