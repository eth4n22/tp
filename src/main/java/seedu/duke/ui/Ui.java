package seedu.duke.ui;

import java.util.Scanner;


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
        printWithSeparator("Welcome to Lebook, your personal book management system!");
    }

    public void printExitMessage() {
        printWithSeparator("Goodbye! See you again.");
    }

    public void printSuccess(String message) {
        printWithSeparator("[SUCCESS] " + message);
    }

    public void printError(String message) {
        printWithSeparator("[ERROR] " + message);
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

