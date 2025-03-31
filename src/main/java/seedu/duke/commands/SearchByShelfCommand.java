package seedu.duke.commands;

import seedu.duke.book.Book;
import seedu.duke.book.BookFinder;
import seedu.duke.book.BookManager;
import seedu.duke.exception.LeBookException;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.List;

/**
 * Command to search for books by their exact shelf ID.
 */
public class SearchByShelfCommand extends Command {

    private final String searchTerm;

    public SearchByShelfCommand(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            this.searchTerm = "";
        } else {
            this.searchTerm = searchTerm.trim(); // Shelf IDs might be case-sensitive depending on your system
        }
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) throws LeBookException {
        if (searchTerm.isEmpty()) {
            throw new LeBookException("Please provide a shelf ID to search for (e.g., AD-0-1).");
        }
        List<Book> books = null;
        BookManager bookManager = BookManager.getBookManagerInstance(books);
        BookFinder finder = new BookFinder(bookManager.getBooks());
        // Finder uses case-insensitive compare by default, adjust if needed
        List<Book> results = finder.findBooksByShelfId(searchTerm);

        if (results.isEmpty()) {
            ui.printMessage("Sorry, no books found with the shelf ID '" + searchTerm + "'.");
        } else {
            ui.printMessage("Found " + results.size() + " book(s) with the shelf ID '" + searchTerm + "':");
            ui.showBookList(results);
        }
    }
}
