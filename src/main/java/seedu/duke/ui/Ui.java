package seedu.duke.ui;

import seedu.duke.book.Book;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Ui {
    private static final Ui uiInstance = new Ui();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final Scanner scanner;

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

    //@@author eth4n22
    public void printWelcomeMessage() {
        printWithSeparator("Welcome to Lebook, your personal book management system!");
    }

    //@@author eth4n22
    public void printHelp() {
        String message = """
                ------------------------------- \s
                 Available Commands: \s
                ------------------------------- \s
                1. add TITLE / AUTHOR / GENRE       - Add a new book. \s
                2. borrow INDEX / MEMBER_NAME      - Borrow a book (using 1-based index). \s
                3. delete b/TITLE / AUTHOR         - Remove book by title and author. \s
                4. delete i/INDEX                  - Remove book by list index (1-based). \s
                5. find CRITERIA TERM              - Search books. \s
                   Criteria: title, author, genre, id \s
                6. help                            - Show this help menu. \s
                7. list                            - List all unique book titles. \s
                8. list borrowed                   - List borrowed books. \s
                9. list overdue                    - List overdue books. \s
                10. list users                     - List users who have overdue books. \s
                11. quantity / TITLE / AUTHOR      - Shows the quantity of the specified book. \s
                12. return INDEX                   - Return a borrowed book (using 1-based index). \s
                13. shelf GENRE / SHELF_NUMBER     - List books on a specific shelf (1-based number). \s
                14. statistics                     - View library statistics. \s
                15. undo                           - Undo the last command (add/delete/borrow/return). \s
                16. bye                            - Exit the program. \s
                ------------------------------- \s
                Supported Genres: \s
                  > romance, adventure, action, horror, mystery, nonfiction, scifi \s
                ------------------------------- \s
                Example Usage: \s
                  add The Lord of the Rings / J.R.R. Tolkien / adventure \s
                  quantity / Harry Potter / J.K. Rowling \s
                  list \s
                  borrow 1 / Alice \s
                  find title lord \s
                  find genre adventure \s
                  find id AD-0-0 \s
                  return 1 \s
                  shelf romance / 1 \s
                  statistics \s
                  undo 3 \s
                  delete i/1 \s
                  bye \s
                """;
        printSeparator();
        System.out.println(message);
        printSeparator();
    }

    //@@author eth4n22
    public void printExitMessage() {
        printWithSeparator("Goodbye! Hope to see you again soon!");
    }

    public void printMessage(String message) {
        System.out.println(message);
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

    /**
     * Displays a formatted list of books.
     * Used by ListCommand, FindCommand, ListBorrowedCommand, ListOverdueCommand etc.
     *
     * @param books The list of books to display.
     */
    public void showBookList(List<Book> books) {
        if (books == null || books.isEmpty()) {
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            // Use getters for clarity and encapsulation
            System.out.printf("%d. %s %s by %s (Genre: %s, Shelf: %s)%n",
                    i + 1,                          // 1-based index for display
                    book.getStatusSymbol(),         // [ ] or [X]

                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getBookID() != null ? book.getBookID() : "N/A");

            if (book.isBorrowed()) {
                String borrower = (book.getBorrowerName() != null && !book.getBorrowerName().equals("null"))
                        ? book.getBorrowerName() : "Unknown Borrower";
                String dueDate = (book.getReturnDueDate() != null)
                        ? book.getReturnDueDate().format(DATE_FORMATTER) : "No Due Date";
                System.out.printf("     Borrowed by: %s (Due: %s)%n", borrower, dueDate);
            }
        }
    }
}
