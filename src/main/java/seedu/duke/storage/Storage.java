package seedu.duke.storage;

import seedu.duke.book.Book;
import seedu.duke.shelving.ShelvesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;

public class Storage {
    private static final String SPLIT_REGEX = "\\|";
    private static final String DIRECTORY_NAME = "data";

    private static final int BOOK_TITLE_INDEX = 0;
    private static final int BOOK_AUTHOR_INDEX = 1;
    private static final int BOOK_STATUS_INDEX = 2;
    private static final int BOOK_ID_INDEX = 3;

    private static final int MAX_SPLIT_NUMBER = 4;

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

    private static Storage instance; // Singleton instance
    private final ShelvesManager shelvesManager;
    private final String filePath;   // Instance variable (not static)

    private Storage(String path) {
        this.filePath = path;
        shelvesManager = ShelvesManager.getShelvesManagerInstance();
    }

    public static Storage getInstance(String path) {
        if (instance == null) {
            instance = new Storage(path);
        }
        return instance;
    }

    //@@author WayneCh0y
    public List<Book> loadFileContents() {
        assert filePath != null : "File path must be initialized before loading";

        try {
            List<Book> bookList = new ArrayList<>();

            File file = new File(filePath);
            if (!file.exists()) {
                return bookList;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String details = scanner.nextLine();
                Book book = getBookFromLoad(details, shelvesManager);
                bookList.add(book);
            }

            scanner.close();
            return bookList;

        } catch (IOException e) {
            System.out.print("ERROR:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    //@@author WayneCh0y
    private static Book getBookFromLoad(String details, ShelvesManager shelvesManager) {
        String[] specifiers = details.split(SPLIT_REGEX);

        String bookTitle = specifiers[BOOK_TITLE_INDEX].trim();
        String bookAuthor = specifiers[BOOK_AUTHOR_INDEX].trim();
        String bookStatus = specifiers[BOOK_STATUS_INDEX].trim();
        String bookID = specifiers[BOOK_ID_INDEX].trim();
        String genre = getGenreFromFile(bookID);

        boolean isBorrowed;
        LocalDate returnDueDate = null;
        isBorrowed = bookStatus.equals("1");
        Book book = new Book(bookTitle, bookAuthor, isBorrowed, returnDueDate, "NIL", 1);
        shelvesManager.addBook(bookTitle, bookAuthor, genre);
        return book;
    }

    //@@author WayneCh0y
    private static String getGenreFromFile(String bookID) {
        String identifier = bookID.split("-", 2)[0];
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
            System.out.print("ERROR:" + e.getMessage());
        }
    }
}
