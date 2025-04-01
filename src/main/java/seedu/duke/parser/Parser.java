package seedu.duke.parser;

import seedu.duke.commands.SearchByTitleCommand;
import seedu.duke.commands.SearchByAuthorCommand;
import seedu.duke.commands.SearchByGenreCommand;
import seedu.duke.commands.SearchByShelfCommand;

import seedu.duke.commands.Command;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteByBookCommand;
import seedu.duke.commands.DeleteByIndexCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.ListBookQuantityCommand;


import seedu.duke.commands.StatisticsCommand;

import seedu.duke.commands.ListBorrowedCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.ListOverdueCommand;
import seedu.duke.commands.ListOverdueUsersCommand;
import seedu.duke.commands.ListShelfCommand;
import seedu.duke.commands.UpdateStatusCommand;
import seedu.duke.commands.UndoCommand;

import seedu.duke.exception.LeBookException;

/**
 * Parses user input and returns the corresponding command.
 * Adheres to Checkstyle rules for formatting and structure.
 */
public class Parser {

    private static final String BYE = "bye";
    private static final String ADD = "add";
    private static final String LIST = "list";
    private static final String BORROW = "borrow";
    private static final String RETURN = "return";
    private static final String DELETE = "delete";
    private static final String HELP = "help";
    private static final String FIND = "find";
    private static final String STATISTICS = "statistics";

    private static final String LIST_OVERDUE = "overdue";
    private static final String LIST_BORROWED = "borrowed";

    private static final String LIST_OVERDUE_USERS = "users";
    private static final String LIST_SHELF = "shelf";
    private static final String LIST_QUANTITY = "quantity";

    private static final String DELETE_BY_INDEX = "i";
    private static final String DELETE_BY_BOOK = "b";

    private static final String UNDO = "undo";
    private static final String STATISTICS = "statistics";

    private static final int SPLIT_INTO_TWO = 2;
    private static final int SPLIT_INTO_THREE = 3;
    private static final int MIN_PARTS_TWO = 2;
    private static final int MIN_PARTS_THREE = 3;

    private static final int LENGTH_LIMIT_THREE = 3;
    private static final int LENGTH_LIMIT_TWO = 2;

    //@@author jenmarieng
    /**
     * Parses the book index (1-based user input) to a 0-based index.
     */
    private static int parseIndex(String indexString) throws LeBookException {
        if (indexString == null || indexString.trim().isEmpty()) {
            throw new LeBookException("Index cannot be empty.");
        }
        try {
            int index = Integer.parseInt(indexString.trim());
            return index - 1;
        } catch (NumberFormatException e) {
            throw new LeBookException("Invalid index format. Please provide a number.");
        }
    }

    /**
     * Parses the borrower's name from the full borrow command details string.
     * Expects details in format "INDEX / MEMBER_NAME".
     */
    private static String parseBorrowCommand(String borrowDetails) throws LeBookException {
        String[] parts = borrowDetails.split("/", SPLIT_INTO_TWO);
        if (parts.length < MIN_PARTS_TWO || parts[1].trim().isEmpty()) {
            throw new LeBookException("Invalid format. Missing borrower name. "
                    + "Use: borrow BOOK_INDEX / MEMBER_NAME");
        }
        return parts[1].trim();
    }

    //@@author jenmarieng
    /**
     * Parses details for the add command.
     * Expects details in format "TITLE / AUTHOR / GENRE".
     */
    private static Command parseAddCommand(String bookDetails) throws LeBookException {
        if (bookDetails == null || bookDetails.trim().isEmpty()) {
            throw new LeBookException("Missing book details. Format: add TITLE / AUTHOR / GENRE");
        }
        String[] parts = bookDetails.split("/", SPLIT_INTO_THREE);
        if (parts.length < MIN_PARTS_THREE) {
            throw new LeBookException("Invalid format. Format: add TITLE / AUTHOR / GENRE");
        }

        String title = parts[0].trim();
        String author = parts[1].trim();
        String genre = parts[2].trim().toLowerCase();

        if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
            throw new LeBookException("Title, Author, and Genre cannot be empty.");
        }
        return new AddCommand(title, author, genre);
    }

    //@@author jenmarieng
    /**
     * Parses details for the list shelf command.
     * Expects details in format "GENRE / SHELF_NUMBER".
     */
    private static Command parseListShelfCommand(String bookDetails) throws LeBookException {
        String[] parts = bookDetails.split("/", SPLIT_INTO_TWO);
        if (parts.length < LENGTH_LIMIT_TWO) {
            throw new LeBookException("Invalid format. It should be: shelf / GENRE / INDEX");
        }

        String[] shelfDetails = parts[1].split("/", SPLIT_INTO_TWO);
        if (shelfDetails.length < LENGTH_LIMIT_TWO) {
            throw new LeBookException("Invalid format. It should be: shelf / GENRE / INDEX");
        }

        String genre = shelfDetails[0].trim().toLowerCase();
        String indexString = shelfDetails[1].trim();
        int shelfIndex = parseIndex(indexString);
        return new ListShelfCommand(genre, shelfIndex);
    }

    //@@author jenmarieng
    /**
     * Parses the details following the "list" command word.
     */
    private static Command parseListCommand(String inputDetails) throws LeBookException {
        String listCommandType = inputDetails.trim().toLowerCase();

        if (listCommandType.isEmpty()) {
            return new ListCommand();
        }

        switch (listCommandType) {
        case LIST_OVERDUE:
            return new ListOverdueCommand();
        case LIST_BORROWED:
            return new ListBorrowedCommand();
        case LIST_OVERDUE_USERS:
            return new ListOverdueUsersCommand();
        default:
            throw new LeBookException("Unknown list type: '" + listCommandType +
                    "'. Valid options: list overdue, list borrowed");
        }
    }

    //@@author jenmarieng
    /**
     * Parses details for the delete command.
     * Expects "i/INDEX" or "b/TITLE/AUTHOR".
     */
    private static Command parseDeleteCommand(String userInput) throws LeBookException {
        String[] parts = userInput.split("/", SPLIT_INTO_TWO);
        if (parts.length < LENGTH_LIMIT_TWO) {
            throw new LeBookException("Invalid format. It should be: delete b/BOOK_TITLE/AUTHOR_NAME "
                    + "or delete i/BOOK_INDEX");
        }
        String deleteCommandType = parts[0].trim();
        switch (deleteCommandType) {
        case DELETE_BY_INDEX:
            int bookIndex = parseIndex(parts[1].trim()); //throws LeBook Exception
            return new DeleteByIndexCommand(bookIndex);
        case DELETE_BY_BOOK:
            String[] bookDetails = parts[1].split("/", SPLIT_INTO_TWO); //should split into title and author
            if (bookDetails.length < LENGTH_LIMIT_TWO) {
                throw new LeBookException("Invalid format. It should be: delete b/BOOK_TITLE/AUTHOR_NAME");
            }
            String bookTitle = bookDetails[0].trim();
            String authorName = bookDetails[1].trim();
            return new DeleteByBookCommand(bookTitle, authorName);
        default:
            throw new LeBookException("Invalid format. It should be: delete b/BOOK_TITLE/AUTHOR_NAME "
                    + "or delete i/BOOK_INDEX");
        }
    }


    //@@author WayneCh0y
    private static Command parseListQuantityCommand(String inputDetails) throws LeBookException {
        String[] parts = inputDetails.split("/", SPLIT_INTO_THREE);
        if (parts.length < LENGTH_LIMIT_THREE) {
            throw new LeBookException("Invalid format. It should be: quantity / BOOK_TITLE / AUTHOR_NAME");
        }
        String title = parts[1].trim();
        String author = parts[2].trim();
        return new ListBookQuantityCommand(title, author);
    }

    //@@author jenmarieng
    /**
     * Parses details for the find command.
     * Expects "CRITERIA SEARCH_TERM".
     */
    private static Command parseFindCommand(String findDetails) throws LeBookException {
        if (findDetails == null || findDetails.trim().isEmpty()) {
            throw new LeBookException("Missing search criteria and term. Format: find CRITERIA SEARCH_TERM");
        }

        String[] parts = findDetails.trim().split("\\s+", 2);
        if (parts.length < MIN_PARTS_TWO || parts[1].trim().isEmpty()) {
            throw new LeBookException("Missing search term. Format: find CRITERIA SEARCH_TERM");
        }

        String criteria = parts[0].toLowerCase().trim();
        String searchTerm = parts[1].trim();

        switch (criteria) {
        case "title":
            return new SearchByTitleCommand(searchTerm);
        case "author":
            return new SearchByAuthorCommand(searchTerm);
        case "genre":
            return new SearchByGenreCommand(searchTerm.toLowerCase());
        case "shelf":
            return new SearchByShelfCommand(searchTerm);
        default:
            throw new LeBookException("Invalid search criteria '" + criteria +
                    "'. Use 'title', 'author', 'genre', or 'shelf'.");
        }
    }

    //@@author jenmarieng
    /**
     * Parses the full user input string into a Command object.
     *
     * @param userInput The raw user input.
     * @return A Command object corresponding to the user input.
     * @throws LeBookException If the input is invalid or cannot be parsed.
     */
    public static Command parse(String userInput) throws LeBookException {
        assert userInput != null : "User input should not be null";

        String trimmedInput = userInput.trim();
        if (trimmedInput.isEmpty()) {
            throw new LeBookException("Command cannot be empty!");
        }

        String[] fullInput = trimmedInput.split(" ", SPLIT_INTO_TWO);
        String commandType = fullInput[0].toLowerCase();
        String inputDetails = (fullInput.length > 1) ? fullInput[1] : "";
        int bookIndex;

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return parseListCommand(inputDetails);
        case BORROW:
            String bookIndexString = inputDetails.split("/")[0].trim();
            bookIndex = parseIndex(bookIndexString);
            String borrowerName = parseBorrowCommand(inputDetails);
            return new UpdateStatusCommand(commandType, bookIndex, borrowerName);
        case RETURN:
            bookIndex = parseIndex(inputDetails);
            return new UpdateStatusCommand(commandType, bookIndex, null);
        case DELETE:
            return parseDeleteCommand(inputDetails);
        case ADD:
            return parseAddCommand(inputDetails);
        case HELP:
            return new HelpCommand();
        case UNDO:
            int count = 1;
            if (!inputDetails.isEmpty()) {
                try {
                    count = Integer.parseInt(inputDetails.trim());
                } catch (NumberFormatException e) {
                    throw new LeBookException("Invalid format. It should be: undo");
                }
            }
            return new UndoCommand(count);
        case FIND:
            return parseFindCommand(inputDetails);
        case LIST_SHELF:
            return parseListShelfCommand(inputDetails);
        case LIST_QUANTITY:
            return parseListQuantityCommand(inputDetails);
        case STATISTICS:
            return new StatisticsCommand();
        default:
            throw new LeBookException("I don't understand. Try starting with list, add, delete, borrow, return!");
        }
    }
}
