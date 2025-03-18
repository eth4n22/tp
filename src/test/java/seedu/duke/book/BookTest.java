package seedu.duke.book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    // --- Additional Fail Cases ---

    @Test
    public void testEmptyTitle_ThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Book("", "J.K. Rowling"));
    }

    @Test
    public void testNullTitle_ThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Book(null, "J.K. Rowling"));
    }

    @Test
    public void testEmptyAuthor_ThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Book("Harry Potter", ""));
    }

    @Test
    public void testNullAuthor_ThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Book("Harry Potter", null));
    }

    @Test
    public void testToFileFormat_BookNotBorrowed() {
        Book book = new Book("Moby Dick", "Herman Melville");
        String expected = "Moby Dick | Herman Melville | 0"; // 0 means not borrowed
        assertEquals(expected, book.toFileFormat());
    }

    @Test
    public void testToFileFormat_BookBorrowed() {
        Book book = new Book("Moby Dick", "Herman Melville");
        book.setStatus(true);
        String expected = "Moby Dick | Herman Melville | 1"; // 1 means borrowed
        assertEquals(expected, book.toFileFormat());
    }

    @Test
    public void testToString_BookNotBorrowed() {
        Book book = new Book("Moby Dick", "Herman Melville");
        String expected = "[ ] Moby Dick (by Herman Melville) ";
        assertEquals(expected, book.toString());
    }

    @Test
    public void testToString_BookBorrowed() {
        Book book = new Book("Moby Dick", "Herman Melville");
        book.setStatus(true);
        String expected = "[X] Moby Dick (by Herman Melville) ";
        assertEquals(expected, book.toString());
    }
}
