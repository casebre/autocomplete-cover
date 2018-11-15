package com.cover.technicalassessment.utils;

public class StringValidationUtil {

    public static boolean hasMinimumRequired(String input, int minChars) {
        return (input != null && input.length() >= minChars);
    }
}
