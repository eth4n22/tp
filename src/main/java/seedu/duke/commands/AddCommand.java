package seedu.duke.commands;

import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class AddCommand extends Command {
    private final String bookDetails;
    private final String genre;

    public AddCommand(String bookDetails, String genre) {
        this.bookDetails = bookDetails;
        this.genre = genre;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "Library should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert bookDetails != null : "Book details cannot be null";
        String response = library.addNewBook(bookDetails, genre);
        ui.printWithSeparator(response);
        storage.writeToFile(library.getBooks());
    }
}
