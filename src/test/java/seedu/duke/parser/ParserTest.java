package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteByIndexCommand;
import seedu.duke.commands.DeleteByBookCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.ListShelfCommand;
import seedu.duke.commands.UpdateStatusCommand;
import seedu.duke.commands.ListOverdueUsersCommand;
import seedu.duke.exception.LeBookException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parse_validBorrowCommand_returnsUpdateStatusCommand() throws LeBookException {
        Command command = Parser.parse("borrow 1 / Alice");
        assertTrue(command instanceof UpdateStatusCommand);
    }

    @Test
    public void parse_invalidBorrowCommand_throwsLeBookException() {
        assertThrows(LeBookException.class, () -> Parser.parse("borrow 1"));
    }

    @Test
    public void parse_invalidReturnCommand_throwsLeBookException() {
        assertThrows(LeBookException.class, () -> Parser.parse("return x"));
    }

    @Test
    public void parse_validAddCommand_returnsAddCommand() throws LeBookException {
        Command command = Parser.parse("add Harry Potter / J.K. Rowling / Fantasy");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_invalidAddCommand_throwsLeBookException() {
        assertThrows(LeBookException.class, () -> Parser.parse("add Harry Potter / J.K. Rowling"));
    }

    @Test
    public void parse_validListShelfCommand_returnsListShelfCommand() throws LeBookException {
        Command command = Parser.parse("shelf / action / 1");
        assertTrue(command instanceof ListShelfCommand);
    }

    @Test
    public void parse_invalidListShelfCommand_throwsLeBookException() {
        assertThrows(LeBookException.class, () -> Parser.parse("list shelf Fiction"));
    }

    @Test
    void testParseValidCommand_listCommand() throws LeBookException {
        Command result = Parser.parse("list");
        assertNotNull(result);
        assertInstanceOf(ListCommand.class, result);
    }

    @Test
    void testParseExitCommand() throws LeBookException {
        Command result = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, result);
    }

    @Test
    void testParseInvalidCommand() {
        Exception exception = assertThrows(LeBookException.class, () -> Parser.parse("invalidCommand"));
        assertEquals("I don't understand. Try starting with list, add, delete, borrow, return!",
                exception.getMessage());
    }

    @Test
    public void parse_emptyInput_assertionErrorThrown() {
        assertThrows(LeBookException.class, () -> Parser.parse(""));
    }

    @Test
    public void parse_spacesInput_assertionErrorThrown() {
        assertThrows(LeBookException.class, () -> Parser.parse("  "));
    }

    @Test
    public void parse_nullInput_assertionErrorThrown() {
        assertThrows(AssertionError.class, () -> Parser.parse(null));
    }

    @Test
    void testParseReturnCommand_validIndex() throws LeBookException {
        Command result = Parser.parse("return 1");
        assertNotNull(result);
        assertInstanceOf(UpdateStatusCommand.class, result);
    }

    @Test
    void testParseReturnCommand_invalidIndex() {
        Exception exception = assertThrows(LeBookException.class, () -> Parser.parse("return xyz"));
        assertEquals("Invalid index format. Please provide a number.", exception.getMessage());
    }

    @Test
    void testParseDeleteCommand_incomplete() {
        Exception exception = assertThrows(LeBookException.class, () -> Parser.parse("delete"));
        assertEquals("Invalid format. It should be: delete b/BOOK_TITLE/AUTHOR_NAME or delete i/BOOK_INDEX"
                , exception.getMessage());
    }

    @Test
    void testParseDeleteCommand_invalidIndex() {
        Exception exception = assertThrows(LeBookException.class, () -> Parser.parse("delete i/abc"));
        assertEquals("Invalid index format. Please provide a number.", exception.getMessage());
    }

    //@@author Deanson Choo
    @Test
    void testParseDeleteCommand_validIndex() throws LeBookException {
        Command result = Parser.parse("delete i/1");
        assertNotNull(result);
        assertInstanceOf(DeleteByIndexCommand.class, result);
    }

    @Test
    void testParseDeleteCommand_invalidBook() {
        Exception exception = assertThrows(LeBookException.class, () -> Parser.parse("delete b/abc"));
        assertEquals("Invalid format. It should be: delete b/BOOK_TITLE/AUTHOR_NAME", exception.getMessage());
    }

    @Test
    void testParseDeleteCommand_validBook() throws LeBookException {
        Command result = Parser.parse("delete b/title/author");
        assertNotNull(result);
        assertInstanceOf(DeleteByBookCommand.class, result);
    }

    @Test
    void testParseValidCommand_listOverdueUsersCommand() throws LeBookException {
        Command result = Parser.parse("list users");
        assertNotNull(result);
        assertInstanceOf(ListOverdueUsersCommand.class, result);
    }
}
