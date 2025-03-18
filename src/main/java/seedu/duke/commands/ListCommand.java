package seedu.duke.commands;


import seedu.duke.book.BookManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;


public class ListCommand extends Command {
    @Override
    public void execute(BookManager bookManager, Ui ui, Storage storage) {
        assert bookManager != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        String response = bookManager.listBooks();
        ui.printWithSeparator(response);
    }
}
