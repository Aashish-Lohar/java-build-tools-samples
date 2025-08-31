package com.example.demo;

/**
 * Main Application class
 */
public class App {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("Welcome to the Demo Build Tools Application!");
        System.out.println("5 + 3 = " + calculator.add(5, 3));
        System.out.println("5 - 3 = " + calculator.subtract(5, 3));
        System.out.println("5 * 3 = " + calculator.multiply(5, 3));
        System.out.println("6 / 3 = " + calculator.divide(6, 3));
    }
}
