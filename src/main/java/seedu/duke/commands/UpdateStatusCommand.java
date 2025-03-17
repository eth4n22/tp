package seedu.duke.commands;

import seedu.duke.book.BookManager;
import seedu.duke.storage.Storage;

public class UpdateStatusCommand extends Command {

    private String bookDetails;

    public UpdateStatusCommand(String bookDetails) {
        this.bookDetails = bookDetails;
    }
    @Override
    public void execute(BookManager bookManager, Ui ui, Storage storage){
        String response = BookManager.updateBookStatus(bookDetails);
        ui.printWithSeparator(response);
        storage.writeToFile(bookManager.getBooks());
    }

}
