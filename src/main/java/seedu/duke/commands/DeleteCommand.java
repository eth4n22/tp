package seedu.duke.commands;

import seedu.duke.book.BookManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
        private String bookIndex;
        public DeleteCommand(String Index) {
            bookIndex= Index;
        }
        @Override
        public void execute(BookManager bookManager, Ui ui, Storage storage) {
            String response = bookManager.deleteBook(bookIndex);
            ui.printWithSeparator(response);
            storage.writeToFile(bookManager.getBooks());
        }
    }

