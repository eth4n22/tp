package seedu.duke.shelf;

import seedu.duke.Shelving.Shelves.Shelf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ShelfTest {
    private Shelf shelf;

    @BeforeEach
    public void setUp() {
        shelf = new Shelf(1, "Fiction"); // Initialize a shelf before each test
    }

    @Test
    public void addBookToShelf_validInput_addsBookAndUpdatesCount() {
        String result = shelf.addBookToShelf("The Hobbit / J.R.R. Tolkien", 0);

        assertEquals("\nNow you have 1 books in the Shelf.", result);
        assertEquals(1, shelf.getBooksCurrentlyOnShelf());
        assertFalse(shelf.isFull());
    }
}
