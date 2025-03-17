package seedu.duke.commands;

import seedu.duke.book.BookManager;
import seedu.duke.storage.Storage;

import java.util.List;

public class ListCommand extends Command {
    @Override
    public void execute(BookManager bookManager, Ui ui, Storage storage) {
        List<Book> books = bookManager.getBooks();
        if (books.isEmpty()) {
            ui.printWithSeparator("No books found!");
            return;
        }

        StringBuilder response = new StringBuilder("Here are the books stored:");
        for (int i = 0; i < books.size(); i++) {
            response.append("\n").append(i + 1).append(". ").append(books.get(i));
        }
        ui.printWithSeparator(response.toString());
    }
}
