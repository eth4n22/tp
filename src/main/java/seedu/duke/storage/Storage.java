package seedu.duke.storage;

import seedu.duke.book.Book;
import seedu.duke.exception.LeBookException;
import seedu.duke.member.Member;
import seedu.duke.member.MemberManager;
import seedu.duke.shelving.ShelvesManager;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;

//@@author WayneCh0y
public class Storage {
    private static final String SPLIT_REGEX = "\\|";
    private static final String DIRECTORY_NAME = "data";

    private static final int BOOK_TITLE_INDEX = 0;
    private static final int BOOK_AUTHOR_INDEX = 1;
    private static final int BOOK_STATUS_INDEX = 2;
    private static final int BOOK_DUE_DATE_INDEX = 3;
    private static final int BOOK_SHELF_INDEX = 4;
    private static final int BORROWER_NAME_INDEX = 5;

    private static final int MAX_SPLIT_NUMBER = 6;

    private static final String ROMANCE = "romance";
    private static final String ADVENTURE = "adventure";
    private static final String ACTION = "action";
    private static final String HORROR = "horror";
    private static final String MYSTERY = "mystery";
    private static final String NONFICTION = "nonfiction";
    private static final String SCIFI = "scifi";

    private static final String ROMANCE_ID = "R";
    private static final String ADVENTURE_ID = "AD";
    private static final String ACTION_ID = "AC";
    private static final String HORROR_ID = "H";
    private static final String MYSTERY_ID = "MY";
    private static final String NONFICTION_ID = "NF";
    private static final String SCIFI_ID = "SCIF";
    private static final String CLEAR_FILE_MESSAGE = "[INFO] File cleared successfully.";
    private static final String FILE_CLEAR_FAIL_MESSAGE = "[ERROR] Failed to clear file: ";
    private static final String BORROWED_IDENTIFYER = "1";
    private static final String NOT_BORROWED_IDENTIFYER = "0";
    private static final String TAMPERED_FILE_MESSAGE = "Stop messing with my storage text file!";
    private static final String DUPLICATE_BOOK_ID_MESSAGE = "Duplicate book ID found in storage!";
    private static final String ERROR = "[ERROR] ";
    private static final String BOOK_ID_SPLIT_REGEX = "-";

    private static Storage instance;
    private final ShelvesManager shelvesManager;
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path and initializes the {@code ShelvesManager}.
     *
     * @param path The path to the storage file.
     */
    //@@author WayneCh0y
    private Storage(String path) {
        this.filePath = path;
        shelvesManager = ShelvesManager.getShelvesManagerInstance();
    }

    /**
     * Returns the singleton instance of {@code Storage}. If it does not exist, a new one is created.
     *
     * @param path The path to the storage file.
     * @return The singleton instance of {@code Storage}.
     */
    //@@author WayneCh0y
    public static Storage getInstance(String path) {
        if (instance == null) {
            instance = new Storage(path);
        }
        return instance;
    }

    /**
     * Clears the contents of the storage file.
     * If the file cannot be cleared, an error message is printed to the console.
     */
    public void clearFile() {
        try {
            new FileWriter(filePath, false).close();
            System.out.println(CLEAR_FILE_MESSAGE);
        } catch (IOException e) {
            System.out.println(FILE_CLEAR_FAIL_MESSAGE + e.getMessage());
        }
    }

    //@@author WayneCh0y
    /**
     * Loads book data from the storage file into memory.
     * If the file is corrupted or contains invalid data, the shelves are cleared and the file is wiped.
     *
     * @param memberManager The {@code MemberManager} used to assign books to their borrowers.
     * @return A list of {@code Book} objects loaded from the file. Returns an empty list on failure.
     */
    public List<Book> loadFileContents(MemberManager memberManager) {
        assert filePath != null : "File path must be initialized before loading";

        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Scanner scanner = new Scanner(file)) {
            return loadBooks(scanner, memberManager);
        } catch (FileNotFoundException e) {
            System.out.println(ERROR + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Reads book entries from the scanner and converts them into {@code Book} objects.
     * If any entry is invalid or causes an exception, the shelves are reset and the file is cleared.
     *
     * @param scanner        The {@code Scanner} to read lines from the file.
     * @param memberManager  The {@code MemberManager} used to link borrowed books to their borrowers.
     * @return A list of valid {@code Book} objects parsed from the file.
     */
    private List<Book> loadBooks(Scanner scanner, MemberManager memberManager) {
        List<Book> bookList = new ArrayList<>();
        List<String> idList = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            try {
                Book book = parseBook(line, idList);
                if (book.isBorrowed()) {
                    attachBorrower(book, memberManager);
                }
                bookList.add(book);
            } catch (IOException | LeBookException e) {
                handleCorruptedFile(e);
                return new ArrayList<>();
            }
        }

        return bookList;
    }

    /**
     * Parses a single line of book data from the file and constructs a {@code Book} object.
     *
     * @param line    The line of text containing book information.
     * @param idList  A list of book IDs encountered so far, used to check for duplicates.
     * @return A {@code Book} object constructed from the parsed line.
     * @throws IOException       If the line format is invalid or incomplete.
     * @throws LeBookException   If the book data is invalid or contains a duplicate ID.
     */
    private Book parseBook(String line, List<String> idList) throws IOException, LeBookException {
        String[] parts = line.split(SPLIT_REGEX);
        if (parts.length < MAX_SPLIT_NUMBER) {
            throw new IOException("Invalid data format: " + line);
        }

        String title = parts[BOOK_TITLE_INDEX].trim();
        String author = parts[BOOK_AUTHOR_INDEX].trim();
        String status = parts[BOOK_STATUS_INDEX].trim();
        String dueDateStr = parts[BOOK_DUE_DATE_INDEX].trim();
        String shelfID = parts[BOOK_SHELF_INDEX].trim();
        String borrower = parts[BORROWER_NAME_INDEX].trim();

        validateBookData(title, author, status, dueDateStr, shelfID, borrower, idList);

        boolean isBorrowed = status.equals(BORROWED_IDENTIFYER);
        LocalDate dueDate = isBorrowed ? LocalDate.parse(dueDateStr) : null;
        String genre = getGenreFromFile(shelfID);

        Book book = new Book(title, author, isBorrowed, dueDate, shelfID, borrower);
        shelvesManager.addBook(title, author, genre);
        return book;
    }

    /**
     * Validates individual fields of book data for correctness and duplication.
     *
     * @param title     The title of the book.
     * @param author    The author of the book.
     * @param status    The borrowing status of the book.
     * @param dueDate   The due date (if borrowed).
     * @param id        The shelf ID of the book.
     * @param borrower  The name of the borrower (if borrowed).
     * @param idList    The list of existing book IDs to check for duplicates.
     * @throws LeBookException If any field is invalid or the ID is a duplicate.
     */
    private void validateBookData(String title, String author, String status, String dueDate,
                                  String id, String borrower, List<String> idList) throws LeBookException {
        if (title.isEmpty() || author.isEmpty() || 
                status.isEmpty() || dueDate.isEmpty() ||
                id.isEmpty() || borrower.isEmpty() || 
                (!status.equals(BORROWED_IDENTIFYER) && !status.equals(NOT_BORROWED_IDENTIFYER))) {
            throw new LeBookException(TAMPERED_FILE_MESSAGE);
        }

        if (idList.contains(id)) {
            throw new LeBookException(DUPLICATE_BOOK_ID_MESSAGE);
        }

        idList.add(id);
    }

    /**
     * Assigns the specified {@code Book} to its borrower using the {@code MemberManager}.
     *
     * @param book           The book that is borrowed.
     * @param memberManager  The manager used to locate the borrower.
     */
    private void attachBorrower(Book book, MemberManager memberManager) {
        String borrowerName = book.getBorrowerName();
        if (borrowerName != null && !borrowerName.isEmpty()) {
            Member borrower = memberManager.getMemberByName(borrowerName);
            borrower.borrowBook(book);
        }
    }

    /**
     * Handles the case of a corrupted file by logging the error, cleaning up shelves,
     * and clearing the file contents.
     *
     * @param e The exception that occurred during file parsing.
     */
    private void handleCorruptedFile(Exception e) {
        System.out.println(ERROR + e.getMessage());
        shelvesManager.cleanup();
        MemberManager.getInstance().cleanup();
        clearFile();
    }

    //@@author WayneCh0y
    /**
     * Determines the genre of a book from its shelf ID.
     *
     * @param bookID The ID of the book, where the prefix indicates its genre.
     * @return The genre name as a string, or an empty string if the genre is unknown.
     */
    private static String getGenreFromFile(String bookID) {
        String identifier = bookID.split(BOOK_ID_SPLIT_REGEX, 2)[0];
        switch (identifier) {
        case ROMANCE_ID:
            return ROMANCE;
        case ADVENTURE_ID:
            return ADVENTURE;
        case ACTION_ID:
            return ACTION;
        case HORROR_ID:
            return HORROR;
        case MYSTERY_ID:
            return MYSTERY;
        case NONFICTION_ID:
            return NONFICTION;
        case SCIFI_ID:
            return SCIFI;
        default:
            return "";
        }
    }

    //@@author WayneCh0y
    public void writeToFile(List<Book> bookList) {
        assert bookList != null : "Book list cannot be null";

        File directory = new File(DIRECTORY_NAME);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // Overwrites file
            for (Book book : bookList) {
                writer.write(book.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.print(ERROR + e.getMessage());
        }
    }

    //@@author WayneCh0y
    public void cleanup() {
        try {
            Files.deleteIfExists(Paths.get(filePath)); // Ensure file is deleted
            Files.createFile(Paths.get(filePath));     // Recreate empty file
            instance = null;
        } catch (IOException e) {
            System.out.println("ERROR: Failed to clear file - " + e.getMessage());
        }
    }
}
