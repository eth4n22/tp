package seedu.duke.shelf;

import seedu.duke.Shelving.Shelves.Shelf;
import seedu.duke.Shelving.ShelvesManager;
import seedu.duke.book.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ShelfTest {
    private Shelf shelf;
    private ShelvesManager shelves;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        shelf = new Shelf(1, "Fiction");
        shelves = new ShelvesManager();
    }

    @Test
    public void addBookToShelf_validInput_addsBookAndUpdatesCount() {
        String result = shelf.addBookToShelf("The Hobbit / J.R.R. Tolkien");

        assertEquals("Added: The Hobbit by: J.R.R. Tolkien\nNow you have 1 books in the Shelf.", result);
        assertEquals(1, shelf.getBooksCurrentlyOnShelf());
        assertFalse(shelf.isFull());
    }

    @Test
    public void addBookToShelf_shelfFull_returnsErrorMessage() {
        // Fill the shelf to max capacity
        for (int i = 0; i < Shelf.getMAX_BOOKS_ON_SHELF(); i++) {
            shelf.addBookToShelf("Book " + i + " / Author");
        }

        String result = shelf.addBookToShelf("Extra Book / Author");
        assertEquals("The shelf is full!", result);
        assertTrue(shelf.isFull());
    }

    @Test
    public void addBookToShelf_setsCorrectBookId() {
        shelf.addBookToShelf("Dune / Frank Herbert");
        Book addedBook = shelf.getShelfBooks().get(0);

        assertEquals("Fiction-1-0", addedBook.getBookID());
    }

    @Test
    public void addBookToShelf_bookIndexZero_worksCorrectly() {
        shelf.addBookToShelf("1984 / George Orwell");
        Book addedBook = shelf.getShelfBooks().get(0);

        assertEquals("Fiction-1-0", addedBook.getBookID());
    }

    @Test
    public void addMultipleBooksToShelf() {
        shelf.addBookToShelf("Harry Potter / J.K Rowling");
        shelf.addBookToShelf("The Outsiders / S.E. Hinton");
        shelf.addBookToShelf("The Hunger Games / Suzanne Collins");

        System.setOut(new PrintStream(outContent));
        shelf.listShelf();
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void addBookUsingShelvesManager() {
        String result = shelves.addBook("Romeo and Juliet / William Shakespeare", "romance");
        String newResult = shelves.addBook("The Notebook / Nicholas Sparks", "romance");
        assertEquals("Added: Romeo and Juliet by: William Shakespeare\nNow you have 1 books in the Shelf.", result);
        assertEquals("Added: The Notebook by: Nicholas Sparks\nNow you have 2 books in the Shelf.", newResult);
    }
}
