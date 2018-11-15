package com.cover.technicalassessment.utils;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

public class StringValidationUtil {

    public static boolean hasMinimumRequired(String input, int minChars) {
        return (input != null && input.length() >= minChars);
    }

    public static List<String> filterStringList(@NonNull List<String> list, @NonNull String keyword) {
        List<String> filteredList = new LinkedList<>();
        try {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).toLowerCase().contains(keyword.toLowerCase())) {
                    filteredList.add(list.get(i));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return filteredList;
    }

    public static boolean matchesAny(@NonNull List<String> list, @NonNull String keyword) {
        try {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase(keyword)) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }
}
