package seedu.duke.storage;

import seedu.duke.book.BookManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    private static final String SPLIT_REGEX = "\\|";

    private static final int BOOK_NAME_INDEX = 0;

    private static final int BOOK_STATUS = 1;

    private static final String DIRECTORY_NAME = "data";

    public Storage(String path) {
        filePath = path;
    }

    public static String toSaveAsString(Book book) {
        assert path != null && !path.trim().isEmpty() : "File path cannot be null or empty";

        return book.getBookDescription() + "|" + book.getSaveDescription() + "|" + book.getStatus();
    }

    public static BookManager load() throws IOException {
        assert filePath != null : "File path must be initialized before loading";

        File f = new File(filePath);
        if (!f.exists()) {
            return new BookManager();
        }
        Scanner s = new Scanner(f);
        BookManager bookManager = new BookManager();

        while (s.hasNext()) {
            String details = s.nextLine();
            String[] specifiers = details.split(SPLIT_REGEX);
            String bookName = specifiers[BOOK_NAME_INDEX];

            Book book = new Book(bookName, specifiers[BOOK_STATUS]);
            BookManager.addBook(book);
        }

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        return bookManager;
    }

    public static void save(List<Book> bookList) throws IOException {
        assert bookList != null : "Book list cannot be null";

        File directory = new File(DIRECTORY_NAME);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // Overwrites file
            for (Book book : bookList) {
                writer.write(toSaveAsString(book));
                writer.newLine();
            }
        }
    }
}
