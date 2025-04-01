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
 * Command to search for books by their title.
 */
public class SearchByTitleCommand extends Command {

    private final String searchTerm;

    public SearchByTitleCommand(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            // Initialize with empty string or handle upstream in Parser, decided to handle in Parser
            this.searchTerm = "";
        } else {
            this.searchTerm = searchTerm.trim();
        }
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) throws LeBookException {
        // Shouldn't happen if Parser validates, but as a safeguard:
        if (searchTerm.isEmpty()) {
            throw new LeBookException("Please provide a title to search for.");
        }

        BookManager bookManager = library.getBookManager();
        BookFinder finder = new BookFinder(bookManager.getBooks());
        List<Book> results = finder.findBooksByTitle(searchTerm);

        if (results.isEmpty()) {
            ui.printMessage("Sorry, no books found with titles containing '" + searchTerm + "'.");
        } else {
            ui.printMessage("Found " + results.size() + " book(s) with titles containing '" + searchTerm + "':");
            ui.showBookList(results);
        }
    }
}
