package seedu.duke.book;

import seedu.duke.commands.Command;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.Stack;

public class UndoManager {
    private final Stack<Command> commandHistory;

    public UndoManager() {
        commandHistory = new Stack<>();
    }

    public void pushCommand(Command command) {
        commandHistory.push(command);
    }

    public void undoCommands(int count, Library library, Ui ui, Storage storage, MemberManager memberManager) {
        if (commandHistory.isEmpty()) {
            ui.printError("No commands to undo!");
            return;
        }

        for (int i = 0; i < count; i++) {
            if (commandHistory.isEmpty()) {
                ui.printError("No more commands to undo!");
                break;
            }
            Command lastCommand = commandHistory.pop();
            lastCommand.undo(library, ui, storage, memberManager);
            ui.printSuccess("Successfully undone: " + lastCommand.getCommandDescription());
        }
    }
}
