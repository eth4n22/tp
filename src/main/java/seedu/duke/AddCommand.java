package seedu.duke;

public class AddCommand extends Command {
    private final String bookDetails;

    public AddCommand(String bookDetails) {
        this.bookDetails = bookDetails;
    }

    @Override
    public void execute(BookManager bookManager, Ui ui, Storage storage){
        String response = bookManager.addNewBook(bookDetails);
        ui.printWithSeparator(response);
        storage.writeToFile(bookManager.getBooks());
    }
}
