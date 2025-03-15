/**
 * Lebook Class represents the main chatbot system
 * Initializes storage, UI, task handling, and command parsing
 */

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Lebook {
    private Storage storage;
    private BookList bookList;
    private Ui ui;
    private Parser parser;


    public Lebook(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            bookList = new BookList(storage.load());
        } catch (IOException e) {
            ui.printError("Error loading books: " + e.getMessage());
            bookList = new BookList();
        }
    }


    public void run() {
        ui.printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String userInput = scanner.nextLine().trim();
                String command = parser.extractCommand(userInput);

                switch (command) {
                    case "bye":
                        ui.printExitMessage();
                        return;

                    case "list":
                        bookList.printBookList();
                        break;

                    case "find":
                        String keyword = parser.extractKeyword(userInput);
                        bookList.findBooks(keyword);
                        break;

                    default:
                        bookList.processCommand(userInput, parser);
                }

                storage.save(bookList.getBooks());

            } catch (LebookException e) {
                ui.printError(e.getMessage());
            } catch (Exception e) {
                ui.printError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Main entry point for the chatbot.
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new Lebook("./data/Lebook.txt").run();
    }
}
