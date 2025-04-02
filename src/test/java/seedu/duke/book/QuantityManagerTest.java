package seedu.duke.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seedu.duke.exception.LeBookException;

//@@author jenmarieng
public class QuantityManagerTest {
    private QuantityManager quantityManager;

    @BeforeEach
    public void setUp() {
        quantityManager = QuantityManager.getQuantityManagerInstance();
    }

    @Test
    public void testGetHowManyBorrowed() {
        int borrowedQuantity = quantityManager.getHowManyBorrowed("Dune", "Frank Herbet");
        assertEquals(0, borrowedQuantity);
    }

    @Test
    public void testGetHowManyBooksValidInput() throws LeBookException {
        int quantity = quantityManager.getHowManyBooks("Dune", "Frank Herbet");
        assertEquals(0, quantity);
    }

    @Test
    public void testGetHowManyBooksEmptyTitle() {
        LeBookException exception = assertThrows(LeBookException.class, () -> quantityManager.getHowManyBooks("  ",
                "Frank Herbet"));
        assertEquals("Both the book title and author cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHowManyBooksEmptyAuthor() {
        LeBookException exception = assertThrows(LeBookException.class, () -> quantityManager.getHowManyBooks(
                "Dune", "   "));
        assertEquals("Both the book title and author cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHowManyBooksNullTitle() {
        LeBookException exception = assertThrows(LeBookException.class, () -> quantityManager.getHowManyBooks(null,
                "Frank Herbet"));
        assertEquals("Both the book title and author cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHowManyBooksNullAuthor() {
        LeBookException exception = assertThrows(LeBookException.class, () -> quantityManager.getHowManyBooks(
                "Dune", null));
        assertEquals("Both the book title and author cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHowManyBooksBothEmpty() {
        LeBookException exception = assertThrows(LeBookException.class, () -> quantityManager.getHowManyBooks(
                "", ""));
        assertEquals("Both the book title and author cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHowManyBooksBothNull() {
        LeBookException exception = assertThrows(LeBookException.class, () -> quantityManager.getHowManyBooks(
                null, null));
        assertEquals("Both the book title and author cannot be empty.", exception.getMessage());
    }
}
