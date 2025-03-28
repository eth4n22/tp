package seedu.duke.commands;

import seedu.duke.book.BookManager;
import seedu.duke.library.Library;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class AddCommand extends Command {
    private final String bookDetails;

    public AddCommand(String bookDetails) {
        this.bookDetails = bookDetails;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        assert library != null : "Library should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert bookDetails != null : "Book details cannot be null";
        String response = library.addNewBook(bookDetails);
        ui.printWithSeparator(response);
        storage.writeToFile(library.getBooks());
    }
}
