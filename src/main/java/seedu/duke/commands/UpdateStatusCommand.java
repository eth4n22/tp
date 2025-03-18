package seedu.duke.commands;

import seedu.duke.book.BookManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class UpdateStatusCommand extends Command {

    private String bookDetails;

    public UpdateStatusCommand(String bookDetails) {
        this.bookDetails = bookDetails;
    }
    @Override
    public void execute(BookManager bookManager, Ui ui, Storage storage){
        String response = bookManager.updateBookStatus(bookDetails);
        ui.printWithSeparator(response);
        try {
            storage.writeToFile(bookManager.getBooks());
        } catch (IOException e) {
            ui.printWithSeparator(e.getMessage());
        }
    }

}
