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
 * Command to search for books by their genre.
 */
public class SearchByGenreCommand extends Command {
    public static final String MESSAGE_INVALID_GENRE = "Invalid genre provided. Please use one of the supported genres:" +
            "\n" + "  romance, adventure, action, horror, mystery, nonfiction, scifi";

    private final String searchTerm;

    public SearchByGenreCommand(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            this.searchTerm = "";
        } else {
            // Genre search is case-insensitive but store term as provided by parser (usually lowercased)
            this.searchTerm = searchTerm.trim();
        }
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) throws LeBookException {
        if (searchTerm.isEmpty()) {
            throw new LeBookException("Please provide a genre to search for.");
        }

        BookManager bookManager = library.getBookManager();

        // --- Validate Genre FIRST ---
        if (!bookManager.isValidGenre(searchTerm)) {
            throw new LeBookException(MESSAGE_INVALID_GENRE);
        }

        BookFinder finder = new BookFinder(bookManager.getBooks());
        List<Book> results = finder.findBooksByGenre(searchTerm); // Finder uses case-insensitive compare

        if (results.isEmpty()) {
            ui.printMessage("Sorry, no books found with the genre '" + searchTerm + "'.");
        } else {
            ui.printMessage("Found " + results.size() + " book(s) with the genre '" + searchTerm + "':");
            ui.showBookList(results);
        }
    }
}
