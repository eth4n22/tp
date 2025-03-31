//package seedu.duke.commands;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seedu.duke.library.Library;
//import seedu.duke.member.MemberManager;
//import seedu.duke.storage.Storage;
//import seedu.duke.ui.Ui;
//import seedu.duke.book.Book;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AddCommandTest {
//    private Library library;
//    private Ui ui;
//    private Storage storage;
//    private MemberManager memberManager;
//
//    @BeforeEach
//    void setUp() {
//        library = Library.getTheOneLibrary(new ArrayList<>());
//        ui = Ui.getUiInstance();
//        storage = Storage.getInstance("data/test_data.txt");
//        memberManager = MemberManager.getInstance();
//    }
//
//    @Test
//    void execute_addValidBook_success() {
//        AddCommand command = new AddCommand("Title", "Author", "romance");
//        command.execute(library, ui, storage, memberManager);
//        assertEquals(1, library.getBooks().size());
//        assertEquals("Title", library.getBooks().get(0).getTitle());
//    }
//
//    @Test
//    void undo_addValidBook_success() {
//        AddCommand command = new AddCommand("Title", "Author", "romance");
//        command.execute(library, ui, storage, memberManager);
//        command.undo(library, ui, storage, memberManager);
//        assertEquals(0, library.getBooks().size());
//    }
//}

