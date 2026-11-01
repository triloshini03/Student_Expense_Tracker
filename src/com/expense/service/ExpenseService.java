package com.expense.service;

import com.expense.model.Expense;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

    private List<Expense> expenses;

    // Constructor
    public ExpenseService() {
        expenses = new ArrayList<>();
    }

    // Add a new expense
    public void addExpense(Expense expense) {
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    // View all expenses
    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.println("\n--- All Expenses ---");

        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    // Delete an expense using ID
    public boolean deleteExpense(int id) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                expenses.remove(expense);
                System.out.println("Expense deleted successfully!");
                return true;
            }
        }

        System.out.println("Expense not found.");
        return false;
    }

    // Calculate total expenses
    public double getTotalExpense() {
        double total = 0;

        for (Expense expense : expenses) {
            total = total + expense.getAmount();
        }

        return total;
    }

    // Get all expenses
    public List<Expense> getExpenses() {
        return expenses;
    }
}