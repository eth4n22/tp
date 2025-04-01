package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.book.Book;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
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
    public void testExecute_validBook_bookAdded() {
        AddCommand addCommand = new AddCommand("The Hobbit", "J.R.R. Tolkien", "adventure");
        addCommand.execute(library, ui, storage, memberManager);

        assertEquals(1, library.getBooks().size());
        Book addedBook = library.getBooks().get(0);
        assertEquals("The Hobbit", addedBook.getTitle());
        assertEquals("J.R.R. Tolkien", addedBook.getAuthor());
        assertEquals("adventure", addedBook.getGenre());
    }

    @Test
    public void testExecute_unsupportedGenre_bookNotAdded() {
        AddCommand addCommand = new AddCommand("Unknown Book", "Unknown Author", "unsupported");
        addCommand.execute(library, ui, storage, memberManager);

        assertEquals(0, library.getBooks().size());
    }
}

