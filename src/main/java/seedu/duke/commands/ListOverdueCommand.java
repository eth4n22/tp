package seedu.duke.commands;

import seedu.duke.book.BookManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ListOverdueCommand extends Command {

    @Override
    public void execute(BookManager bookManager, Ui ui, Storage storage) {
        String result = bookManager.listOverdueBooks();
        ui.printWithSeparator(result);
    }
}
