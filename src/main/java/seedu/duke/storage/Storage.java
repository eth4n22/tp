package seedu.duke.storage;

import seedu.duke.book.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    private static final String SPLIT_REGEX = "\\|";

    private static final int BOOK_TITLE_INDEX = 0;
    private static final int BOOK_AUTHOR_INDEX = 1;
    private static final int BOOK_STATUS_INDEX = 2;


    private static final String DIRECTORY_NAME = "data";

    public Storage(String path) {
        filePath = path;
    }

    public String toSaveAsString(Book book) {
        assert (filePath != null) && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        return book.toFileFormat();
    }


    public List<Book> loadFileContents() {
        assert filePath != null : "File path must be initialized before loading";

        try {
            List<Book> bookList = new ArrayList<>();
            File f = new File(filePath);
            if (!f.exists()) {
                return bookList;
            }

            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String details = s.nextLine();
                String[] specifiers = details.split(SPLIT_REGEX);

                String bookTitle = specifiers[BOOK_TITLE_INDEX];
                String bookAuthor = specifiers[BOOK_AUTHOR_INDEX];
                String bookStatus = specifiers[BOOK_STATUS_INDEX];


                boolean isBorrowed;
                isBorrowed = bookStatus.equals("1");

                Book book = new Book(bookTitle, bookAuthor, isBorrowed);

                bookList.add(book);
            }

            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }

            return bookList;
        } catch (IOException e) {
            System.out.print("ERROR:" + e.getMessage());
        }

        return new ArrayList<>();
    }

    public void writeToFile(List<Book> bookList) {
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
        } catch (IOException e) {
            System.out.print("ERROR:" + e.getMessage());
        }
    }
}
