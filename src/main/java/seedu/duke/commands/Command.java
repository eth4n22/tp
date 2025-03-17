package seedu.duke.commands;

import seedu.duke.book.BookManager;
import seedu.duke.exception.LeBookException;
import seedu.duke.storage.Storage;

public abstract class Command {
    public abstract void execute(BookManager bookManager, Ui ui, Storage storage) throws LeBookException;
    public boolean isExit() { return false; }
}
