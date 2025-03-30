package seedu.duke.shelf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.book.Book;
import seedu.duke.shelving.shelves.Shelf;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListShelfCommandTest {
    private Shelf shelf;

    @BeforeEach
    void setUp() {
        shelf = new Shelf(1, "FIC"); // Example shelf with index 1 and genre "FIC"
    }

    @Test
    void listShelf_emptyShelf_returnsNoBooksMessage() {
        assertEquals("No books on shelf", shelf.listShelf());
    }

    @Test
    void listShelf_allDummyBooks_returnsEmptyString() {
        shelf.getShelfBooks().add(new Book("Title1", "duMmY"));
        shelf.getShelfBooks().add(new Book("Title2", "duMmY"));

        assertEquals("", shelf.listShelf());
    }

    @Test
    void listShelf_mixedBooks_returnsOnlyValidBooks() {
        shelf.getShelfBooks().add(new Book("Valid Book", "Author A"));
        shelf.getShelfBooks().add(new Book("Another Book", "duMmY"));
        shelf.getShelfBooks().add(new Book("Final Book", "Author B"));

        String expected = "[ ] Valid Book (by Author A) \n[ ] Final Book (by Author B) ";
        assertEquals(expected, shelf.listShelf());
    }
}
