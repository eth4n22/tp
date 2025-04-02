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

public class DeleteByIDCommandTest {

    private Library library;
    private Ui ui;
    private Storage storage;
    private MemberManager memberManager;

    @BeforeEach
    public void setUp() {
        List<Book> bookList = new ArrayList<>();
        library = Library.getTheOneLibrary(bookList);
        ui = Ui.getUiInstance();
        storage = Storage.getInstance("data/test_data.txt");
        memberManager = MemberManager.getInstance();
        library.cleanup();
    }

    @Test
    public void testExecute_validID_bookDeleted() {
        library.addNewBookToCatalogue("Harry Potter", "Rowling", "romance");
        library.addNewBookToShelf("Harry Potter", "Rowling", "romance");
        Book book = library.getBooks().get(0);
        String bookID = book.getBookID();

        DeleteByIDCommand command = new DeleteByIDCommand(bookID);
        command.execute(library, ui, storage, memberManager);

        assertEquals(0, library.getBooks().size());
    }

    @Test
    public void testExecute_invalidID_noBookDeleted() {
        DeleteByIDCommand command = new DeleteByIDCommand("INVALID-ID");
        command.execute(library, ui, storage, memberManager);

        assertEquals(0, library.getBooks().size()); // No book to delete
    }

    @Test
    public void testUndo_bookRestored() {
        library.addNewBookToCatalogue("Harry Potter", "Rowling", "romance");
        library.addNewBookToShelf("Harry Potter", "Rowling", "romance");
        Book book = library.getBooks().get(0);
        String bookID = book.getBookID();

        DeleteByIDCommand command = new DeleteByIDCommand(bookID);
        command.execute(library, ui, storage, memberManager);
        assertEquals(0, library.getBooks().size()); // Book deleted

        command.undo(library, ui, storage, memberManager);
        assertEquals(1, library.getBooks().size()); // Book restored
        assertEquals("Harry Potter", library.getBooks().get(0).getTitle());
        assertEquals("Rowling", library.getBooks().get(0).getAuthor());
    }
}
