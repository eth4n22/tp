package seedu.duke.commands;


import seedu.duke.book.BookManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;



public class ListCommand extends Command {
    @Override
    public void execute(BookManager bookManager, Ui ui, Storage storage) {
        String response = bookManager.listBooks();
        ui.printWithSeparator(response);
    }
}
