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
 * Command to search for books by their author.
 */
public class SearchByAuthorCommand extends Command {

    private final String searchTerm;

    public SearchByAuthorCommand(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            this.searchTerm = "";
        } else {
            this.searchTerm = searchTerm.trim();
        }
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) throws LeBookException {
        if (searchTerm.isEmpty()) {
            throw new LeBookException("Please provide an author name to search for.");
        }
        List<Book> books = null;
        BookManager bookManager = BookManager.getBookManagerInstance(books);
        BookFinder finder = new BookFinder(bookManager.getBooks());
        List<Book> results = finder.findBooksByAuthor(searchTerm);

        if (results.isEmpty()) {
            ui.printMessage("Sorry, no books found with authors containing '" + searchTerm + "'.");
        } else {
            ui.printMessage("Found " + results.size() + " book(s) with authors containing '" + searchTerm + "':");
            ui.showBookList(results);
        }
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
    }

}
