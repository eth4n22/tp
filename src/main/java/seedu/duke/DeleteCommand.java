package seedu.duke;

    public class DeleteCommand extends Command {
        private String[] bookIndex;
        public DeleteCommand(String bookDetails) {
            this.bookIndex= bookIndex;
        }
        @Override
        public void execute(BookManager bookManager, Ui ui, Storage storage) {
            String response = bookManager.deleteBook(bookIndex);
            ui.printWithSeparator(response);
            storage.writeToFile(bookManager.getBooks());
        }
    }

