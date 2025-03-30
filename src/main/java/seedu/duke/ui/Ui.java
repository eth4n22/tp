package seedu.duke.ui;

import java.util.Scanner;


public class Ui {
    private static final Ui uiInstance = new Ui();
    private final Scanner scanner;

    private Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static Ui getUiInstance() {
        return uiInstance;
    }

    public String readCommand() {
        System.out.print("Enter command: "); //EDIT ACCORDINGLY
        return scanner.nextLine();
    }

    public void printWelcomeMessage() {
        printWithSeparator("Welcome to Lebook, your personal book management system!");
    }

    public void printHelp() {
        String message = """
                Available Commands:
                -------------------
                1. add TITLE/AUTHOR/GENRE      - Add a new book to the library.
                2. delete BOOK_NUMBER          - Remove a book from the library.
                3. list                        - List all current books.
                4. list overdue                - List all overdue books.
                5. list borrowed               - List all borrowed books.
                6. borrow BOOK_NUMBER          - Borrow a book from the library.
                7. return BOOK_NUMBER          - Return a borrowed book.
                8. statistics                  - View library statistics.
                9. bye                         - Exit the program.
                10. help                        - Show this help menu.
                11. shelf SHELF/GENRE/NUMBER    - Lists a particular shelf of specified genre.
                -------------------
                Genres:
                  > romance
                  > adventure
                  > action
                  > horror
                  > mystery
                  > nonfiction
                  > scifi
                """;
        printWithSeparator(message);
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

