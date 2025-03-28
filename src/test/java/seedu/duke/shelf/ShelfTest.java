package seedu.duke.shelf;

import seedu.duke.Shelving.Shelves.Shelf;
import seedu.duke.book.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShelfTest {
    private Shelf shelf;

    @BeforeEach
    public void setUp() {
        shelf = new Shelf(1, "Fiction");
    }

    @Test
    public void addBookToShelf_validInput_addsBookAndUpdatesCount() {
        String result = shelf.addBookToShelf("The Hobbit / J.R.R. Tolkien", 0);

        assertEquals("\nNow you have 1 books in the Shelf.", result);
        assertEquals(1, shelf.getBooksCurrentlyOnShelf());
        assertFalse(shelf.isFull());
    }

    @Test
    public void addBookToShelf_shelfFull_returnsErrorMessage() {
        // Fill the shelf to max capacity
        for (int i = 0; i < Shelf.getMAX_BOOKS_ON_SHELF(); i++) {
            shelf.addBookToShelf("Book " + i + " / Author", i);
        }

        String result = shelf.addBookToShelf("Extra Book / Author", Shelf.getMAX_BOOKS_ON_SHELF());
        assertEquals("The shelf is full!", result);
        assertTrue(shelf.isFull());
    }

    @Test
    public void addBookToShelf_setsCorrectBookId() {
        shelf.addBookToShelf("Dune / Frank Herbert", 3);
        Book addedBook = shelf.getShelfBooks().get(0);

        assertEquals("Fiction-1-3", addedBook.getBookID());
    }

    @Test
    public void addBookToShelf_bookIndexZero_worksCorrectly() {
        shelf.addBookToShelf("1984 / George Orwell", 0);
        Book addedBook = shelf.getShelfBooks().get(0);

        assertEquals("Fiction-1-0", addedBook.getBookID());
    }
}
