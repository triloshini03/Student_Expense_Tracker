package com.expense.utils;

public class InputValidator {

    // Check whether the amount is valid
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    // Check whether text is empty
    public static boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    // Check whether the ID is valid
    public static boolean isValidId(int id) {
        return id > 0;
    }
}
