package seedu.duke.commands;

import seedu.duke.book.BookManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;


public class UpdateStatusCommand extends Command {

    private final String bookDetails;

    public UpdateStatusCommand(String bookDetails) {
        this.bookDetails = bookDetails;
    }

    @Override
    public void execute(BookManager bookManager, Ui ui, Storage storage) {
        String response = bookManager.updateBookStatus(bookDetails);
        ui.printWithSeparator(response);
        storage.writeToFile(bookManager.getBooks());
    }
}
