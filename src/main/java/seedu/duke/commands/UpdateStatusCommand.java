package seedu.duke.commands;

import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class UpdateStatusCommand extends Command {

    private final String commandType;
    private final int bookIndex;
    private final String borrowerName;

    public UpdateStatusCommand(String commandType, int bookIndex, String borrowerName) {
        this.commandType = commandType;
        this.bookIndex = bookIndex;
        this.borrowerName = borrowerName;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert commandType != null : "Command type cannot be null";
        
        String response = library.updateBookStatus(commandType, bookIndex, borrowerName, memberManager);
        ui.printWithSeparator(response);
        storage.writeToFile(library.getBooks());
    }
}
