package seedu.duke.commands;

import seedu.duke.book.BookManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;


public class AddCommand extends Command {
    private final String bookDetails;

    public AddCommand(String bookDetails) {
        this.bookDetails = bookDetails;
    }

    @Override
    public void execute(BookManager bookManager, Ui ui, Storage storage) {
        String response = bookManager.addNewBook(bookDetails);
        ui.printWithSeparator(response);
        try {
            storage.writeToFile(bookManager.getBooks());
        } catch (IOException e) {
            ui.printWithSeparator(e.getMessage());
        }
    }
}
