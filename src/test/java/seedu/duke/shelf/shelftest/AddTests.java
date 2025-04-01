package seedu.duke.shelf.shelftest;

import seedu.duke.shelving.shelves.Shelf;
import seedu.duke.shelving.ShelvesManager;
import seedu.duke.book.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AddTests {
    private Shelf shelf;
    private ShelvesManager shelves;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    //@@author WayneCh0y
    @BeforeEach
    public void setUp() {
        shelf = new Shelf(1, "SCIF");
        shelves = ShelvesManager.getShelvesManagerInstance();
    }


    //@@author WayneCh0y
    @Test
    public void addBookToShelf_validInput_addsBookAndUpdatesCount() {
        shelves.cleanup();

        String result = shelf.addBookToShelf("The Hobbit", "J.R.R. Tolkien");

        assertEquals("\nNow you have 1 books on the Shelf: SCIF-1"
                , result);
        assertEquals(1, shelf.getBooksCurrentlyOnShelf());
        assertFalse(shelf.isFull());
        shelves.cleanup();
    }

    //@@author WayneCh0y
    @Test
    public void addBookToShelf_shelfFull_returnsErrorMessage() {
        shelves.cleanup();
        // Fill the shelf to max capacity
        for (int i = 0; i < 100; i++) {
            shelf.addBookToShelf("Book " + i, "Author");
        }

        String result = shelf.addBookToShelf("Extra Book", "Author");
        assertEquals("The shelf is full!", result);
        assertTrue(shelf.isFull());
        shelves.cleanup();
    }

    //@@author WayneCh0y
    @Test
    public void addBookToShelf_setsCorrectBookId() {
        shelves.cleanup();
        shelf.addBookToShelf("Dune", "Frank Herbert");
        Book addedBook = shelf.getShelfBooks().get(0);

        assertEquals("SCIF-1-0", addedBook.getBookID());
        shelves.cleanup();
    }

    //@@author WayneCh0y
    @Test
    public void addBookToShelf_bookIndexZero_worksCorrectly() {
        shelves.cleanup();
        shelf.addBookToShelf("1984", "George Orwell");
        Book addedBook = shelf.getShelfBooks().get(0);

        assertEquals("SCIF-1-0", addedBook.getBookID());
        shelves.cleanup();
    }

    //@@author WayneCh0y
    @Test
    public void addMultipleBooksToShelf() {
        shelves.cleanup();
        shelf.addBookToShelf("Harry Potter", "J.K Rowling");
        shelf.addBookToShelf("The Outsiders", "S.E. Hinton");
        shelf.addBookToShelf("The Hunger Games", "Suzanne Collins");

        System.setOut(new PrintStream(outContent));
        shelf.listShelf();
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
        shelves.cleanup();
    }

    //@@author WayneCh0y
    @Test
    public void addBookUsingShelvesManager() {
        shelves.cleanup();
        String result = shelves.addBook("Romeo and Juliet", "William Shakespeare", "romance");
        String newResult = shelves.addBook("The Notebook", "Nicholas Sparks", "romance");
        assertEquals("\nNow you have 1 books on the Shelf: R-0"
                , result);
        assertEquals("\nNow you have 2 books on the Shelf: R-0"
                , newResult);
        shelves.cleanup();
    }
}
