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

        int undoneCount = 0;
        while (undoneCount < count) {
            if (commandHistory.isEmpty()) {
                ui.printError("No commands to undo!");
                break;
            }
            Command lastCommand = commandHistory.pop();
            if (lastCommand.isUndoable()) {
                lastCommand.undo(library, ui, storage, memberManager);
                ui.printSuccess("Successfully undone: " + lastCommand.getCommandDescription());
                undoneCount++;
            } else {
                ui.printError(lastCommand.getCommandDescription());
            }
        }



    }
}
