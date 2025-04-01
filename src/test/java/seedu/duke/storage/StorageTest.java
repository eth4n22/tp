package seedu.duke.storage;

import seedu.duke.book.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.member.MemberManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    private static final String TEST_FILE_PATH = "data/test_books.txt";

    @BeforeEach
    void setUp() throws IOException {
        Storage.getInstance(TEST_FILE_PATH).cleanup();
        Files.createDirectories(Paths.get("data"));
    }

    @Test
    void loadFileContents_validFile_success() throws IOException {
        Storage.getInstance(TEST_FILE_PATH).cleanup();
        Files.write(Paths.get(TEST_FILE_PATH),
                Collections.singletonList("The Hobbit | J.R.R. Tolkien | 1 | 2025-04-01 | AC-0-0 | 1 | test"));
        Storage storage = Storage.getInstance(TEST_FILE_PATH);
        MemberManager memberManager = MemberManager.getInstance();
        List<Book> books = storage.loadFileContents(memberManager);

        assertEquals(1, books.size());
        Book book = books.get(0);
        assertEquals("The Hobbit", book.getTitle());
        assertEquals("J.R.R. Tolkien", book.getAuthor());
        assertTrue(book.isBorrowed());
        storage.cleanup();
    }

    @Test
    void loadFileContents_emptyFile_returnsEmptyList() {
        Storage.getInstance(TEST_FILE_PATH).cleanup();
        MemberManager memberManager = MemberManager.getInstance();
        List<Book> books = Storage.getInstance(TEST_FILE_PATH).loadFileContents(memberManager);
        assertTrue(books.isEmpty(), "Expected empty list for empty file");
        Storage.getInstance(TEST_FILE_PATH).cleanup();
    }

    @Test
    void writeToFile_validBooks_success() throws IOException {
        Storage.getInstance(TEST_FILE_PATH).cleanup();
        List<Book> books = new ArrayList<>();
        books.add(new Book("1984", "George Orwell", false,
                null, "MY-0-1", ""));

        Storage storage = Storage.getInstance(TEST_FILE_PATH);
        storage.writeToFile(books);

        List<String> fileContents = Files.readAllLines(Paths.get(TEST_FILE_PATH));

        assertEquals(1, fileContents.size(), "Expected one line in file after writing");
        assertTrue(fileContents.get(0).contains("1984"), "File should contain '1984'");
        assertTrue(fileContents.get(0).contains("George Orwell"), "File should contain 'George Orwell'");
        storage.cleanup();
    }

    @Test
    void cleanup_clearsFileContents() throws IOException {
        Storage.getInstance(TEST_FILE_PATH).cleanup();
        Storage storage = Storage.getInstance(TEST_FILE_PATH);

        assertTrue(Files.readAllLines(Paths.get(TEST_FILE_PATH)).isEmpty());
        storage.cleanup();
    }
}
