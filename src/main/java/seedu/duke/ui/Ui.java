package seedu.duke.ui;

import seedu.duke.book.Book; // Import Book
import java.util.List; // Import List
import java.util.Scanner;
import java.time.format.DateTimeFormatter; // Import Formatter

public class Ui {
    private static final Ui uiInstance = new Ui();
    private final Scanner scanner;
    // Formatter for due dates in showBookList
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static Ui getUiInstance() {
        return uiInstance;
    }

    public String readCommand() {
        System.out.print("Enter command: ");
        return scanner.nextLine();
    }

    public void printWelcomeMessage() {
        printWithSeparator("Welcome to Lebook, your personal book management system!");
    }

    // --- Updated Help Message ---
    public void printHelp() {
        String message = """
                Available Commands:
                -------------------
                1.  add TITLE / AUTHOR / GENRE      - Add a new book.
                2.  delete i/INDEX                  - Remove book by list index (1-based).
                3.  delete b/TITLE / AUTHOR         - Remove book by title and author.
                4.  list                            - List all unique book titles.
                5.  list overdue                    - List overdue books.
                6.  list borrowed                   - List borrowed books.
                7.  borrow INDEX / MEMBER_NAME      - Borrow a book (using 1-based index).
                8.  return INDEX                    - Return a borrowed book (using 1-based index).
                9.  find CRITERIA TERM              - Search books.
                      Criteria: title, author, genre, shelf
                10. shelf GENRE / SHELF_NUMBER      - List books on a specific shelf (1-based number).
                11. statistics                      - View library statistics.
                12. help                            - Show this help menu.
                13. bye                             - Exit the program.
                -------------------
                Supported Genres:
                  > romance, adventure, action, horror, mystery, nonfiction, scifi
                -------------------
                Example Usage:
                  add The Lord of the Rings / J.R.R. Tolkien / adventure
                  list
                  borrow 1 / Alice
                  find title lord
                  find genre adventure
                  find shelf AD-0-0
                  return 1
                  delete i/1
                  bye
                """;
        // Print help directly without extra separators
        printSeparator();
        System.out.println(message);
        printSeparator();
    }

    public void printExitMessage() {
        printWithSeparator("Goodbye! Hope to see you again soon!");
    }

    // --- Method for general messages (like search results count) ---
    public void printMessage(String message) {
        System.out.println(message);
    }

    // Keep specific success/error methods if preferred
    public void printSuccess(String message) {
        printWithSeparator("[SUCCESS] " + message);
    }

    public void printError(String message) {
        printWithSeparator("[ERROR] " + message);
    }

    // Keep separator methods
    public void printWithSeparator(String message) {
        printSeparator();
        System.out.println(message);
        printSeparator();
    }

    public void printSeparator() {
        System.out.println("========================================");
    }


    // --- NEW METHOD to display list of books (for list, find, etc.) ---
    /**
     * Displays a formatted list of books.
     * Used by ListCommand, FindCommand, ListBorrowedCommand, ListOverdueCommand etc.
     *
     * @param books The list of books to display.
     */
    public void showBookList(List<Book> books) {
        if (books == null || books.isEmpty()) {
            // The calling command should print "No books found" or similar.
            // This method just handles the display if there *are* books.
            return;
        }
        // Use 1-based indexing for user display
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            // Use getters for clarity and encapsulation
            System.out.printf("%d. %s %s by %s (Genre: %s, Shelf: %s, Qty: %d)%n",
                    i + 1,                          // 1-based index for display
                    book.getStatusSymbol(),         // [ ] or [X]
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),                // Use the getGenre() method
                    book.getBookID() != null ? book.getBookID() : "N/A");

            // Display borrower info and due date if borrowed
            if (book.isBorrowed()) {
                String borrower = (book.getBorrowerName() != null && !book.getBorrowerName().equals("null"))
                        ? book.getBorrowerName() : "Unknown Borrower";
                String dueDate = (book.getReturnDueDate() != null)
                        ? book.getReturnDueDate().format(DATE_FORMATTER) : "No Due Date";
                System.out.printf("     Borrowed by: %s (Due: %s)%n", borrower, dueDate);
            }
        }
        // Optional: Add a separator after the list
        // printSeparator();
    }
}
