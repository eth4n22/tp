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
                Goodbye! See you again.
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

    /*
    @Test
    public void testReadCommand() {
        String input = "test command\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui uiWithInput = Ui.getUiInstance();
        assertEquals("test command", uiWithInput.readCommand());
    }*/

    @Test
    public void testHelpCommand() {
        ui.printHelp();
        String expected = """
                ========================================
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
                
                ========================================
                """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    // Helper function to normalize line endings and trim spaces
    private String normalize(String output) {
        return output.replaceAll("\r\n", "\n").trim();
    }
}
