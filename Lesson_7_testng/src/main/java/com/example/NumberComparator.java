package com.example;

public class NumberComparator {
    public static String compare(int a, int b) {
        if (a > b) return a + " > " + b;
        if (a < b) return a + " < " + b;
        return a + " = " + b;
    }
}