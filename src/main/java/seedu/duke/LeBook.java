import seedu.duke.Command;
import seedu.duke.LeBookException;
import seedu.duke.Ui;
import seedu.duke.parser.Parser;

/**
 * Lebook Class represents the main chatbot system
 * Initializes storage, UI, task handling, and command parsing
 */

public class LeBook {
    private final BookManager bookManager;
    private final Storage storage;
    private final Ui ui;

    public LeBook(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        bookManager = new BookManager(storage.loadFileContents());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(bookManager, ui, storage);
                isExit = command.isExit();
            } catch (LeBookException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new LeBook("data/jeff_data.txt").run();
    }
}

