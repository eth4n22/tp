package seedu.duke.commands;

import seedu.duke.book.Book;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class DeleteByBookCommand extends Command {

    private final String bookTitle;
    private final String bookAuthor;
    private Book deletedBook;

    public DeleteByBookCommand(String bookTitle, String bookAuthor) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        deletedBook = library.getBookByTitleAndAuthor(bookTitle, bookAuthor);

        if (deletedBook == null) {
            ui.printError("Book to delete not found");
            return;
        }

        String response = library.deleteBook(bookTitle, bookAuthor);
        ui.printWithSeparator(response);
        storage.writeToFile(library.getBooks());
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        if (deletedBook != null) {
            library.restoreBook(deletedBook);
            storage.writeToFile(library.getBooks());
        } else {
            ui.printError("Nothing to undo for DeleteByBookCommand");
        }
    }

    @Override
    public String getCommandDescription() {
        return "Delete a book by its title and author";
    }
}
