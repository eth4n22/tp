package seedu.duke.book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookTest {

    @Test
    public void testBookCreation() {
        Book book = new Book("Harry Potter", "J.K. Rowling");
        assertEquals("Harry Potter", book.getTitle());
        assertEquals("J.K. Rowling", book.getAuthor());
        assertFalse(book.isBorrowed()); // Should be false initially
    }

    @Test
    public void testSetStatusBorrowed() {
        Book book = new Book("1984", "George Orwell");
        book.setStatus(true);
        assertTrue(book.isBorrowed());
    }

    @Test
    public void testSetStatusReturned() {
        Book book = new Book("The Hobbit", "J.R.R. Tolkien");
        book.setStatus(false);
        assertFalse(book.isBorrowed());
    }

    @Test
    public void testEmptyTitleThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Book("", "J.K. Rowling"));
    }

    @Test
    public void testNullTitleThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Book(null, "J.K. Rowling"));
    }

    @Test
    public void testEmptyAuthorThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Book("Harry Potter", ""));
    }

    @Test
    public void testNullAuthorThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Book("Harry Potter", null));
    }

    @Test
    public void testToStringBookNotBorrowed() {
        Book book = new Book("Moby Dick", "Herman Melville");
        String expected = "[ ] Moby Dick (by Herman Melville) | Quantity: 1 ";
        assertEquals(expected, book.toString());
    }

    @Test
    public void testToStringBookBorrowed() {
        Book book = new Book("Moby Dick", "Herman Melville");
        book.setStatus(true);
        String expected = "[X] Moby Dick (by Herman Melville) | Quantity: 1 ";
        assertEquals(expected, book.toString());
    }

    @Test
    public void testToFileFormatBookNotBorrowed() {
        Book book = new Book("Moby Dick", "Herman Melville");
        String expected = "Moby Dick | Herman Melville | 0 | NIL | 1";
        assertEquals(expected, book.toFileFormat());
    }

    @Test
    public void testToFileFormatBookBorrowed() {
        Book book = new Book("Moby Dick", "Herman Melville");
        book.setStatus(true);
        String expected = "Moby Dick | Herman Melville | 1 | NIL | 1";
        assertEquals(expected, book.toFileFormat());
    }
}
