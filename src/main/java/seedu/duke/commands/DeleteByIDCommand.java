package seedu.duke.commands;

import seedu.duke.book.Book;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class DeleteByIDCommand extends Command {

    private final String bookID;
    private Book deletedBook;

    public DeleteByIDCommand(String bookID) {
        this.bookID = bookID;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        deletedBook = library.getBookByID(bookID);

        if (deletedBook == null) {
            ui.printError("Book to delete not found");
            return;
        }

        String response = library.deleteBook(bookID);
        ui.printWithSeparator(response);
        storage.writeToFile(library.getBooks());
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        if (deletedBook != null) {
            library.restoreBook(deletedBook);
            storage.writeToFile(library.getBooks());
        } else {
            ui.printError("Nothing to undo for DeleteByIDCommand");
        }
    }

    @Override
    public String getCommandDescription() {
        return "Delete a book by its ID";
    }
}
