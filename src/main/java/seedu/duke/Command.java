package seedu.duke;

public abstract class Command {
    public abstract void execute(BookManager bookManager, Ui ui, Storage storage) throws LeBookException;
    public boolean isExit() { return false; }
}
