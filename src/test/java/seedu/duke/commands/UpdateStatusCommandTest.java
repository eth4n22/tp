package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.book.Book;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateStatusCommandTest {

    private Library library;
    private Ui ui;
    private Storage storage;
    private MemberManager memberManager;

    @BeforeEach
    public void setUp() {
        List<Book> bookList = new ArrayList<>();
        library = Library.getTheOneLibrary(bookList);
        ui = Ui.getUiInstance();
        storage = Storage.getInstance("data/books.txt");
        memberManager = MemberManager.getInstance();
        library.cleanup(); // Reset singleton state
    }

    @Test
    public void testExecute_borrowBook_statusUpdated() {
        // Add a book
        library.addNewBookToCatalogue("The Hobbit", "J.R.R. Tolkien", "adventure");
        library.addNewBookToShelf("The Hobbit", "J.R.R. Tolkien", "adventure");

        UpdateStatusCommand borrowCommand = new UpdateStatusCommand("borrow", 0, "Alice");
        borrowCommand.execute(library, ui, storage, memberManager);

        Book book = library.getBooks().get(0);
        assertTrue(book.isBorrowed());
        assertEquals("Alice", book.getBorrowerName());
        assertNotNull(book.getReturnDueDate());
    }

    @Test
    public void testExecute_returnBook_statusUpdated() {
        // Add and borrow a book
        library.addNewBookToCatalogue("The Hobbit", "J.R.R. Tolkien", "adventure");
        library.addNewBookToShelf("The Hobbit", "J.R.R. Tolkien", "adventure");

        UpdateStatusCommand borrowCommand = new UpdateStatusCommand("borrow", 0, "Alice");
        borrowCommand.execute(library, ui, storage, memberManager);

        UpdateStatusCommand returnCommand = new UpdateStatusCommand("return", 0, "Alice");
        returnCommand.execute(library, ui, storage, memberManager);

        Book book = library.getBooks().get(0);
        assertFalse(book.isBorrowed());
        assertNull(book.getBorrowerName());
        assertNull(book.getReturnDueDate());
    }

    @Test
    public void testUndo_borrowCommand_bookRestored() {
        // Add and borrow a book
        library.addNewBookToCatalogue("The Hobbit", "J.R.R. Tolkien", "adventure");
        library.addNewBookToShelf("The Hobbit", "J.R.R. Tolkien", "adventure");

        UpdateStatusCommand borrowCommand = new UpdateStatusCommand("borrow", 0, "Alice");
        borrowCommand.execute(library, ui, storage, memberManager);

        // Undo borrow
        borrowCommand.undo(library, ui, storage, memberManager);

        Book book = library.getBooks().get(0);
        assertFalse(book.isBorrowed());
        assertNull(book.getBorrowerName());
        assertNull(book.getReturnDueDate());
    }

    @Test
    public void testUndo_returnCommand_bookRestored() {
        // Add and borrow a book
        library.addNewBookToCatalogue("The Hobbit", "J.R.R. Tolkien", "adventure");
        library.addNewBookToShelf("The Hobbit", "J.R.R. Tolkien", "adventure");

        UpdateStatusCommand borrowCommand = new UpdateStatusCommand("borrow", 0, "Alice");
        borrowCommand.execute(library, ui, storage, memberManager);

        // Return book
        UpdateStatusCommand returnCommand = new UpdateStatusCommand("return", 0, "Alice");
        returnCommand.execute(library, ui, storage, memberManager);

        // Undo return
        returnCommand.undo(library, ui, storage, memberManager);

        Book book = library.getBooks().get(0);
        assertTrue(book.isBorrowed());
        assertEquals("Alice", book.getBorrowerName());
        assertNotNull(book.getReturnDueDate());
    }
}

