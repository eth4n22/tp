package seedu.duke.commands;

import seedu.duke.book.BookManager;
import seedu.duke.storage.Storage;

public class ExitCommand extends Command {

    @Override
    public void execute(BookManager bookManager, Ui ui, Storage storage){
    }

    @Override
    public boolean isExit() {
        return true;
    }
}