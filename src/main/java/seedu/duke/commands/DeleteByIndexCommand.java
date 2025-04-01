package seedu.duke.commands;

import seedu.duke.book.Book;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class DeleteByIndexCommand extends Command {

    private final int bookIndex;
    private Book deletedBook;

    public DeleteByIndexCommand(int index) {
        this.bookIndex = index;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (bookIndex < 0 || bookIndex >= library.getBooks().size()) {
            ui.printError("Invalid book index to delete.");
            return;
        }

        deletedBook = library.getBooks().get(bookIndex);

        String response = library.deleteBook(bookIndex);
        ui.printWithSeparator(response);
        storage.writeToFile(library.getBooks());
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        if (deletedBook != null) {
            library.restoreBook(deletedBook);
            storage.writeToFile(library.getBooks());
        } else {
            ui.printError("Nothing to undo for DeleteByIndexCommand.");
        }
    }

    @Override
    public String getCommandDescription() {
        return "Delete a book by its index";
    }
}
