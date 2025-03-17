import seedu.duke.Ui;

/**
 * Lebook Class represents the main chatbot system
 * Initializes storage, UI, task handling, and command parsing
 */

public class LeBook {
    private final BookManager bookManager;
    private final Storage storage;
    private final Ui ui;

    public LeBook(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";

        ui = new Ui();
        storage = new Storage(filePath);
        bookManager = new BookManager(storage.loadFileContents());

        // Assertions to check if critical components are initialized
        assert ui != null : "UI instance should not be null";
        assert storage != null : "Storage instance should not be null";
        assert bookManager != null : "BookManager instance should not be null";
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                assert userInput != null : "User input should not be null";

                Command command = Parser.parse(userInput);
                assert command != null : "Parser should return a valid Command object";

                command.execute(bookManager, ui, storage);
                isExit = command.isExit();
            } catch (LeBookException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        assert args != null : "Program arguments should not be null";
        new LeBook("data/jeff_data.txt").run();
    }
}
