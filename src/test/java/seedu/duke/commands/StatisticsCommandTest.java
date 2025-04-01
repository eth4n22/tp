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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class StatisticsCommandTest {

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
        library.cleanup();
    }

    @Test
    public void testExecute_statisticsPrintedSuccessfully() {
        library.addNewBookToCatalogue("Book One", "Author A", "adventure");
        library.addNewBookToShelf("Book One", "Author A", "adventure");

        library.addNewBookToCatalogue("Book Two", "Author B", "mystery");
        library.addNewBookToShelf("Book Two", "Author B", "mystery");

        StatisticsCommand statsCommand = new StatisticsCommand();
        assertDoesNotThrow(() -> statsCommand.execute(library, ui, storage, memberManager));
        storage.cleanup();
    }
}

