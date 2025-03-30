package seedu.duke.storage;

import seedu.duke.book.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    private static final String TEST_FILE_PATH = "data/test_books.txt";

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get("data"));
        Files.write(Paths.get(TEST_FILE_PATH),
                Collections.singletonList("The Hobbit | J.R.R. Tolkien | 1"));
    }

    @Test
    void loadFileContents_validFile_success() {
        Storage storage = Storage.getInstance(TEST_FILE_PATH);
        List<Book> books = storage.loadFileContents();

        assertEquals(1, books.size());
        Book book = books.get(0);
        assertEquals("The Hobbit", book.getTitle());
        assertEquals("J.R.R. Tolkien", book.getAuthor());
        assertTrue(book.isBorrowed());
    }
}
