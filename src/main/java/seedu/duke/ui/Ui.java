package seedu.duke.ui;

import java.util.Scanner;

/**
 * UI Class
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.print("Enter command: "); //EDIT ACCORDINGLY
        return scanner.nextLine().trim();
    }

    public void printWelcomeMessage() {
        printSeparator();
        System.out.println("Welcome to Lebook, your personal book management system!");
        /**
         * possible implementation for v2.0
         * System.out.println("Type 'help' to see available commands.");
         */
        printSeparator();
    }

    public void printExitMessage() {
        printSeparator();
        System.out.println("Goodbye! See you again.");
        printSeparator();
    }

    public void printSuccess(String message) {
        printSeparator();
        System.out.println("[SUCCESS] " + message);
        printSeparator();
    }

    public void printError(String message) {
        printSeparator();
        System.out.println("[ERROR] " + message);
        printSeparator();
    }

    public void printWithSeparator(String message) {
        printSeparator();
        System.out.println(message);
        printSeparator();
    }

    public void printSeparator() {
        System.out.println("========================================");
    }
}

