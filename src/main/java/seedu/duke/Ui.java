/**
 * UI Class
 */
public class Ui {

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

    public void printSeparator() {
        System.out.println("========================================");
    }
}

