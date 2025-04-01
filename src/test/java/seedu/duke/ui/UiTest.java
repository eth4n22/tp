package seedu.duke.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private Ui ui;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ui = Ui.getUiInstance();
    }

    @Test
    public void testPrintWelcomeMessage() {
        ui.printWelcomeMessage();
        String expected = """
                ========================================
                Welcome to Lebook, your personal book management system!
                ========================================
                """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    @Test
    public void testPrintExitMessage() {
        ui.printExitMessage();
        String expected = """
                ========================================
                Goodbye! Hope to see you again soon!
                ========================================
                """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    @Test
    public void testPrintSuccessMessage() {
        ui.printSuccess("Book added successfully!");
        String expected = """
                ========================================
                [SUCCESS] Book added successfully!
                ========================================
                """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    @Test
    public void testPrintErrorMessage() {
        ui.printError("Invalid command.");
        String expected = """
                ========================================
                [ERROR] Invalid command.
                ========================================
                """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    @Test
    public void testPrintWithSeparator() {
        ui.printWithSeparator("This is a test message.");
        String expected = """
                ========================================
                This is a test message.
                ========================================
                """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    @Test
    public void testHelpCommand() {
        ui.printHelp();
        String expected = """
                ========================================
                -------------------------------
                 Available Commands:
                -------------------------------
                1. add TITLE / AUTHOR / GENRE      - Add a new book.
                2. delete i/INDEX                  - Remove book by list index (1-based).
                3. delete b/TITLE / AUTHOR         - Remove book by title and author.
                4. list                            - List all unique book titles.
                5. list overdue                    - List overdue books.
                6. list borrowed                   - List borrowed books.
                7. borrow INDEX / MEMBER_NAME      - Borrow a book (using 1-based index).
                8. return INDEX                    - Return a borrowed book (using 1-based index).
                9. find CRITERIA TERM              - Search books.
                   Criteria: title, author, genre, shelf
                10. shelf GENRE / SHELF_NUMBER     - List books on a specific shelf (1-based number).
                11. statistics                     - View library statistics.
                12. undo                           - Undo the last command (add/delete/borrow/return).
                13. help                           - Show this help menu.
                14. bye                            - Exit the program.
                -------------------------------
                Supported Genres:
                  > romance, adventure, action, horror, mystery, nonfiction, scifi
                -------------------------------
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
                ========================================
                """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    // Helper function to normalize line endings and trim spaces
    private String normalize(String output) {
        return output.replaceAll("\r\n", "\n").trim();
    }
}
