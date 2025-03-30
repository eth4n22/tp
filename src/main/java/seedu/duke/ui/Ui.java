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
                2. delete i/BOOK_NUMBER        - Remove a book from the library by index
                3. delete b/BOOK_TITLE/AUTHOR  - Remove a book from the library by title
                4. list                        - List all current books.
                5. borrow BOOK_NUMBER          - Borrow a book from the library.
                6. return BOOK_NUMBER          - Return a borrowed book.
                7. bye                         - Exit the program.
                8. help                        - Show this help menu.
                9. shelf SHELF/GENRE/NUMBER    - Lists a particular shelf of specified genre.
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

    public void printShelf() {

    }

    public void printSeparator() {
        System.out.println("========================================");
    }
}

