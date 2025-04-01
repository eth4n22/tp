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

public class DeleteByBookCommandTest {

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
    public void testExecute_validBook_bookDeleted() {
        // Add a book to delete
        library.addNewBookToCatalogue("The Hobbit", "J.R.R. Tolkien", "adventure");
        library.addNewBookToShelf("The Hobbit", "J.R.R. Tolkien", "adventure");

        assertEquals(1, library.getBooks().size());

        DeleteByBookCommand deleteCommand = new DeleteByBookCommand("The Hobbit", "J.R.R. Tolkien");
        deleteCommand.execute(library, ui, storage, memberManager);

        assertEquals(0, library.getBooks().size());
        storage.cleanup();
    }

    @Test
    public void testExecute_bookNotFound_noDeletion() {
        DeleteByBookCommand deleteCommand = new DeleteByBookCommand("Unknown Book", "Unknown Author");
        deleteCommand.execute(library, ui, storage, memberManager);

        assertEquals(0, library.getBooks().size());
    }

    @Test
    public void testUndo_restoreDeletedBook_success() {
        // Add and delete a book
        library.addNewBookToCatalogue("The Hobbit", "J.R.R. Tolkien", "adventure");
        library.addNewBookToShelf("The Hobbit", "J.R.R. Tolkien", "adventure");

        DeleteByBookCommand deleteCommand = new DeleteByBookCommand("The Hobbit", "J.R.R. Tolkien");
        deleteCommand.execute(library, ui, storage, memberManager);

        assertEquals(0, library.getBooks().size());

        // Undo deletion
        deleteCommand.undo(library, ui, storage, memberManager);

        assertEquals(1, library.getBooks().size());
        Book restoredBook = library.getBooks().get(0);
        assertEquals("The Hobbit", restoredBook.getTitle());
        assertEquals("J.R.R. Tolkien", restoredBook.getAuthor());
        storage.cleanup();
    }

    @Test
    public void testUndo_noDeletedBook_noRestoration() {
        DeleteByBookCommand deleteCommand = new DeleteByBookCommand("The Hobbit", "J.R.R. Tolkien");
        deleteCommand.undo(library, ui, storage, memberManager);

        assertEquals(0, library.getBooks().size());
    }
}

