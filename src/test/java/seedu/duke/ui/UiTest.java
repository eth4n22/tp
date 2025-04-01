package seedu.duke.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        String output = normalize(outputStream.toString());
        assertTrue(output.contains("Available Commands:"));
        assertTrue(output.contains("add TITLE / AUTHOR / GENRE"));
        assertTrue(output.contains("delete i/INDEX"));
        assertTrue(output.contains("list overdue"));
        assertTrue(output.contains("undo"));
        assertTrue(output.contains("help"));
        assertTrue(output.contains("bye"));
        assertTrue(output.contains("Supported Genres:"));
        assertTrue(output.contains("romance"));
        assertTrue(output.contains("scifi"));
    }

    // Helper function to normalize line endings and trim spaces
    private String normalize(String output) {
        return output.replaceAll("\r\n", "\n").trim();
    }
}
