package seedu.duke.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookManagerTest {
//    private BookManager bookManager;
//
//    @BeforeEach
//    public void setUp() {
//        bookManager = new BookManager(new ArrayList<>());
//    }
//
//    @Test
//    public void testAddNewBook_validFormat() {
//        String result = bookManager.addNewBook("The Great Gatsby / F. Scott Fitzgerald");
//
//        assertEquals(1, bookManager.getBooks().size());
//        assertTrue(result.contains("I've added:"));
//        assertTrue(result.contains("The Great Gatsby"));
//        assertTrue(result.contains("F. Scott Fitzgerald"));
//    }
//
//    @Test
//    public void testAddNewBook_invalidFormat() {
//        String result = bookManager.addNewBook("Invalid Book Format");
//
//        assertEquals(0, bookManager.getBooks().size());
//        assertEquals("Invalid book format! It should be 'TITLE / AUTHOR'.", result);
//    }
//
//    @Test
//    public void testAddNewBook_emptyTitle() {
//        String result = bookManager.addNewBook(" / Some Author");
//
//        assertEquals(0, bookManager.getBooks().size());
//        assertEquals("Book title cannot be empty!", result);
//    }
//
//    @Test
//    public void testAddNewBook_emptyAuthor() {
//        String result = bookManager.addNewBook("Some Title / ");
//
//        assertEquals(0, bookManager.getBooks().size());
//        assertEquals("Book author cannot be empty!", result);
//    }
//
//    @Test
//    public void testDeleteBook_validIndex() {
//        bookManager.addNewBook("Book to Delete / Author");
//        assertEquals(1, bookManager.getBooks().size());
//
//        String result = bookManager.deleteBook(0); //parser passes in 0-indexed bookIndex
//
//        assertEquals(0, bookManager.getBooks().size());
//        assertTrue(result.contains("Book deleted:"));
//        assertTrue(result.contains("Book to Delete"));
//    }
//
//    @Test
//    public void testDeleteBook_invalidIndex() {
//        bookManager.addNewBook("Some Book / Author");
//
//        String result = bookManager.deleteBook(2); // Index out of bounds
//
//        assertEquals(1, bookManager.getBooks().size());
//        assertEquals("There is no such book in the library!", result);
//    }
//
//    @Test
//    public void testDeleteBook_negativeIndex() {
//        String result = bookManager.deleteBook(-1);
//
//        assertEquals("There is no such book in the library!", result);
//    }
//
//    @Test
//    public void testListBooks_emptyLibrary() {
//        String result = bookManager.listBooks();
//
//        assertEquals("No books in the library yet.", result);
//    }
//
//    @Test
//    public void testListBooks_withBooks() {
//        bookManager.addNewBook("Book 1 / Author 1");
//        bookManager.addNewBook("Book 2 / Author 2");
//
//        String result = bookManager.listBooks();
//
//        assertTrue(result.contains("Here are the books in your library:"));
//        assertTrue(result.contains("1. [ ] Book 1 (by Author 1)"));
//        assertTrue(result.contains("2. [ ] Book 2 (by Author 2)"));
//        assertTrue(result.contains("Total books: 2"));
//    }
//
//    @Test
//    public void testUpdateBookStatus_borrow() {
//        bookManager.addNewBook("Test Book / Test Author");
//
//        String result = bookManager.updateBookStatus("borrow", 0); //parser passes in 0-indexed bookIndex
//
//        assertEquals("Borrowed: Test Book", result);
//        assertTrue(bookManager.getBooks().get(0).isBorrowed());
//    }
//
//    @Test
//    public void testUpdateBookStatus_return() {
//        bookManager.addNewBook("Test Book / Test Author");
//        bookManager.updateBookStatus("borrow", 1); // Borrow first
//
//        String result = bookManager.updateBookStatus("return", 0); //parser passes in 0-indexed bookIndex
//
//        assertEquals("Returned: Test Book", result);
//        assertFalse(bookManager.getBooks().get(0).isBorrowed());
//    }
//
//    @Test
//    public void testUpdateBookStatus_invalidIndex() {
//        bookManager.addNewBook("Test Book / Test Author");
//
//        String result = bookManager.updateBookStatus("borrow", 2); // Invalid index
//
//        assertEquals("There is no such book in the library!", result);
//    }
//
//    @Test
//    void testBorrowBook() {
//        bookManager.addNewBook("Harry Potter / Wayne");
//        bookManager.addNewBook("I Love 2113 / Deanson");
//
//        String result = bookManager.updateBookStatus("borrow", 0); //parser passes in 0-indexed bookIndex
//
//        assertEquals("Borrowed: Harry Potter", result);
//
//        assertTrue(bookManager.getBooks().get(0).isBorrowed());
//    }
//
//    @Test
//    void testReturnBook() {
//        bookManager.addNewBook("Harry Potter / Wayne");
//        bookManager.addNewBook("I Love 2113 / Deanson");
//        bookManager.updateBookStatus("borrow", 2);
//
//        String result = bookManager.updateBookStatus("return", 1); //parser passes in 0-indexed bookIndex
//
//        assertEquals("Returned: I Love 2113", result);
//
//        assertFalse(bookManager.getBooks().get(0).isBorrowed());
//    }
//
//    @Test
//    void testInvalidBookNumber() {
//        bookManager.addNewBook("Harry Potter / Wayne");
//        bookManager.addNewBook("I Love 2113 / Deanson");
//        String result = bookManager.updateBookStatus("borrow", 100);
//
//        assertEquals("There is no such book in the library!", result);
//    }
}
