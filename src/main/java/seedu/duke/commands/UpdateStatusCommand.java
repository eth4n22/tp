package seedu.duke.commands;

import seedu.duke.book.Book;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
/**
 * Commands for changing the status of books to borrowed or returned
 */
public class UpdateStatusCommand extends Command {

    private final String commandType;
    private final int bookIndex;
    private final String borrowerName;

    //variables for undo function
    private boolean previousStatus;
    private String previousBorrowerName;
    private LocalDate previousDueDate;

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

        //Save previous state before changes
        Book book = library.getBooks().get(bookIndex);
        previousStatus = book.isBorrowed();
        previousBorrowerName = book.getBorrowerName();
        previousDueDate = book.getReturnDueDate();

        String response = library.updateBookStatus(commandType, bookIndex, borrowerName, memberManager);
        ui.printWithSeparator(response);
        storage.writeToFile(library.getBooks());
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        Book book = library.getBooks().get(bookIndex);
        book.setStatus(previousStatus);
        book.setBorrowerName(previousBorrowerName);
        book.setReturnDueDate(previousDueDate);

        storage.writeToFile(library.getBooks());
    }

    @Override
    public String getCommandDescription() {
        if (commandType.equals("borrow")) {
            return "Borrow Command";
        } else if (commandType.equals("return")) {
            return "Return Command";
        } else {
            return "Update Status Command";
        }
    }
}
