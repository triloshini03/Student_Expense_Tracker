package com.expense.storage;

import com.expense.model.Expense;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILE_PATH = "data/expenses.txt";

    // Save all expenses to a file
    public void saveExpenses(List<Expense> expenses) {
        try {
            File dataFolder = new File("data");

            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(FILE_PATH)
            );

            for (Expense expense : expenses) {
                String data = expense.getId() + "," +
                        expense.getCategory() + "," +
                        expense.getAmount() + "," +
                        expense.getDate() + "," +
                        expense.getDescription();

                writer.write(data);
                writer.newLine();
            }

            writer.close();
            System.out.println("Expenses saved successfully!");

        } catch (IOException e) {
            System.out.println("Error while saving expenses: " + e.getMessage());
        }
    }

    // Load expenses from the file
    public List<Expense> loadExpenses() {

        List<Expense> expenses = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return expenses;
        }

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(file)
            );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 5) {
                    int id = Integer.parseInt(data[0]);
                    String category = data[1];
                    double amount = Double.parseDouble(data[2]);
                    String date = data[3];
                    String description = data[4];

                    Expense expense = new Expense(
                            id,
                            category,
                            amount,
                            date,
                            description
                    );

                    expenses.add(expense);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error while loading expenses: " + e.getMessage());
        }

        return expenses;
    }
}