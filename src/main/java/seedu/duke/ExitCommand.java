package seedu.duke;

public class ExitCommand extends Command {

    @Override
    public void execute(BookManager bookManager, Ui ui, Storage storage){
    }

    @Override
    public boolean isExit() {
        return true;
    }
}