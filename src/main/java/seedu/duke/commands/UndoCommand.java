package seedu.duke.commands;

import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class UndoCommand extends Command {

    private final int undoCount;

    public UndoCommand(int undoCount) {
        this.undoCount = undoCount;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        library.getUndoManager().undoCommands(undoCount, library, ui, storage, memberManager);
    }

    @Override
    public String getCommandDescription() {
        return "Undo Command (" + undoCount + " times)";
    }
}
