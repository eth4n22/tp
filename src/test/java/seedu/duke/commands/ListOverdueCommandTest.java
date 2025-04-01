package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.book.Book;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ListOverdueCommandTest {

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
    public void testExecute_withOverdueBooks_printsSuccessfully() {
        library.addNewBookToCatalogue("Old Book", "Author A", "adventure");
        library.addNewBookToShelf("Old Book", "Author A", "adventure");

        Book overdueBook = library.getBooks().get(0);
        overdueBook.setStatus(true);
        overdueBook.setBorrowerName("Alice");
        overdueBook.setReturnDueDate(LocalDate.now().minusDays(5)); // Overdue

        ListOverdueCommand overdueCommand = new ListOverdueCommand();
        assertDoesNotThrow(() -> overdueCommand.execute(library, ui, storage, memberManager));
        storage.cleanup();
    }

    @Test
    public void testExecute_noOverdueBooks_printsSuccessfully() {
        library.addNewBookToCatalogue("New Book", "Author B", "mystery");
        library.addNewBookToShelf("New Book", "Author B", "mystery");

        ListOverdueCommand overdueCommand = new ListOverdueCommand();
        assertDoesNotThrow(() -> overdueCommand.execute(library, ui, storage, memberManager));
        storage.cleanup();
    }
}

