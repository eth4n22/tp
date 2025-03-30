package seedu.duke.book;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.duke.member.Member;
import seedu.duke.member.MemberManager;


public class BookManagerTest {
    private BookManager bookManager;

    @BeforeEach
    public void setUp() {
        bookManager = new BookManager(new ArrayList<>());
    }

    @Test
    public void testAddNewBook_validFormat() {
        String result = bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald",
                "romance", "R-0-0");

        assertEquals(1, bookManager.getBooks().size());
        assertTrue(result.contains("I've added:"));
        assertTrue(result.contains("The Great Gatsby"));
        assertTrue(result.contains("F. Scott Fitzgerald"));
    }

    @Test
    public void testAddNewBook_emptyTitle() {
        String result = bookManager.addNewBookToCatalogue("", "Some Author", "romance",
                "R-0-0");

        assertEquals(0, bookManager.getBooks().size());
        assertEquals("Book title cannot be empty!", result);
    }

    @Test
    public void testAddNewBook_emptyAuthor() {
        String result = bookManager.addNewBookToCatalogue("Some Title", "", "romance",
                "R-0-0");

        assertEquals(0, bookManager.getBooks().size());
        assertEquals("Book author cannot be empty!", result);
    }

    @Test
    public void testDeleteBook_validIndex() {
        bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald", "romance",
                "R-0-0");
        assertEquals(1, bookManager.getBooks().size());

        String result = bookManager.deleteBook(0); //parser passes in 0-indexed bookIndex

        assertEquals(0, bookManager.getBooks().size());
        assertTrue(result.contains("Book deleted:"));
        assertTrue(result.contains("The Great Gatsby"));
    }

    @Test
    public void testDeleteBook_invalidIndex() {
        String action = bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald",
                "romance", "R-0-0");

        String result = bookManager.deleteBook(2); // Index out of bounds

        assertEquals(1, bookManager.getBooks().size());
        assertEquals("There is no such book in the library!", result);
    }

    @Test
    public void testDeleteBook_negativeIndex() {
        String result = bookManager.deleteBook(-1);

        assertEquals("There is no such book in the library!", result);
    }

    @Test
    public void testListBooks_emptyLibrary() {
        String result = bookManager.listBooks();

        assertEquals("No books in the library yet.", result);
    }

    @Test
    public void testListBooks_withBooks() {
        String action1 = bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald",
                "romance", "R-0-0");
        String action2 = bookManager.addNewBookToCatalogue("The Hunger Games", "Suzanne",
                "romance", "R-0-1");

        String result = bookManager.listBooks();

        assertTrue(result.contains("Here are the books in your library:"));
        assertTrue(result.contains("1. [ ] The Great Gatsby (by F. Scott Fitzgerald)"));
        assertTrue(result.contains("2. [ ] The Hunger Games (by Suzanne)"));
        assertTrue(result.contains("Total books: 2"));
    }

    @Test
    public void testUpdateBookStatus_borrow() {
        String action = bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald",
                "romance", "R-0-0");
        MemberManager memberManager = new MemberManager();
        Member borrower = memberManager.getMemberByName("John");

        String result = bookManager.updateBookStatus("borrow", 0, "John",
                memberManager);

        assertEquals("John has borrowed: The Great Gatsby", result);
        assertTrue(bookManager.getBooks().get(0).isBorrowed());
    }

    @Test
    public void testUpdateBookStatus_return() {
        bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald",
                "romance", "R-0-0");
        MemberManager memberManager = new MemberManager();
        bookManager.updateBookStatus("borrow", 0, "John", memberManager);

        String result = bookManager.updateBookStatus("return", 0, "John",
                memberManager);

        assertEquals("Returned: The Great Gatsby", result);
        assertFalse(bookManager.getBooks().get(0).isBorrowed());
    }

    @Test
    public void testUpdateBookStatus_invalidIndex() {
        bookManager.addNewBookToCatalogue("Test Book", "Test Author", "romance", "R-0-0");
        MemberManager memberManager = new MemberManager();

        String result = bookManager.updateBookStatus("borrow", 2, "John",
                memberManager);

        assertEquals("There is no such book in the library!", result);
    }

    @Test
    void testBorrowBook() {
        bookManager.addNewBookToCatalogue("Harry Potter", "Wayne", "romance", "R-0-0");
        bookManager.addNewBookToCatalogue("I Love 2113", "Deanson", "romance", "R-0-1");
        MemberManager memberManager = new MemberManager();

        String result = bookManager.updateBookStatus("borrow", 0, "John",
                memberManager);
        //parser passes in 0-indexed bookIndex

        assertEquals("John has borrowed: Harry Potter", result);
        assertTrue(bookManager.getBooks().get(0).isBorrowed());
    }

    @Test
    void testReturnBook() {
        bookManager.addNewBookToCatalogue("Harry Potter", "Wayne", "romance", "R-0-0");
        bookManager.addNewBookToCatalogue("I Love 2113", "Deanson", "romance", "R-0-1");
        MemberManager memberManager = new MemberManager();
        bookManager.updateBookStatus("borrow", 0, "John", memberManager);

        String result = bookManager.updateBookStatus("return", 0, "John",
                memberManager);

        assertEquals("Returned: Harry Potter", result);

        assertFalse(bookManager.getBooks().get(0).isBorrowed());
    }

    @Test
    void testInvalidBookNumber() {
        bookManager.addNewBookToCatalogue("Harry Potter", "Wayne", "romance", "R-0-0");
        bookManager.addNewBookToCatalogue("I Love 2113", "Deanson", "romance", "R-0-1");
        MemberManager memberManager = new MemberManager();
        String result = bookManager.updateBookStatus("borrow", 100, "John",
                memberManager);

        assertEquals("There is no such book in the library!", result);
    }

    @Test
    void testAlreadyBorrowed() {
        bookManager.addNewBookToCatalogue("Harry Potter", "Wayne", "romance", "R-0-0");
        MemberManager memberManager = new MemberManager();
        bookManager.updateBookStatus("borrow", 0, "John", memberManager);

        String result = bookManager.updateBookStatus("borrow", 0, "Jane",
                memberManager);

        assertEquals("This book is already borrowed!", result);
    }

    @Test
    void testNotBorrowed() {
        bookManager.addNewBookToCatalogue("Harry Potter", "Wayne", "romance", "R-0-0");
        MemberManager memberManager = new MemberManager();

        String result = bookManager.updateBookStatus("return", 0, "John",
                memberManager);

        assertEquals("This book is not borrowed!", result);
    }

    @Test
    void testListOverdueBooks() {
        bookManager.addNewBookToCatalogue("Overdue Book","Wayne", "romance", "R-0-0");
        MemberManager memberManager = new MemberManager();
        bookManager.updateBookStatus("borrow", 0, "John", memberManager);
        bookManager.getBooks().get(0).setReturnDueDate(LocalDate.now().minusDays(2));

        String result = bookManager.listOverdueBooks();
        assertTrue(result.contains("Overdue Book"));
    }

    @Test
    void testStatistics() {
        bookManager.addNewBookToCatalogue("Moby A", "Author A", "romance", "R-0-0");
        bookManager.addNewBookToCatalogue("Moby B", "Author B", "romance", "R-0-1");
        MemberManager memberManager = new MemberManager();
        bookManager.updateBookStatus("borrow", 0, "John", memberManager);

        bookManager.getBooks().get(0).setReturnDueDate(LocalDate.now().minusDays(2));

        String stats = bookManager.getStatistics();
        assertTrue(stats.contains("Total books copies: 2"));
        assertTrue(stats.contains("Unique titles: 2"));
        assertTrue(stats.contains("Total books borrowed: 1"));
        assertTrue(stats.contains("Total books overdue: 1"));
    }

    @Test
    void testAddNewBookIncreaseQuantity() {
        // Add a book
        String result1 = bookManager.addNewBookToCatalogue("The Great Gatsby",
                "F. Scott Fitzgerald", "romance", "R-0-0");
        assertEquals(1, bookManager.getBooks().size());
        assertTrue(result1.contains("I've added:"));

        // Add the same book again (should increase quantity, not add new book)
        String result2 = bookManager.addNewBookToCatalogue("The Great Gatsby",
                "F. Scott Fitzgerald", "romance", "R-0-0");
        assertEquals(1, bookManager.getBooks().size()); // Still only one entry
        assertTrue(result2.contains("Increased quantity"));
        assertEquals(2, bookManager.getBooks().get(0).getQuantity());
    }

    @Test
    void testDeleteBookDecreaseQuantity() {
        // Add book twice → quantity becomes 2
        bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald", "romance", "R-0-0");
        bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald", "romance", "R-0-0");

        assertEquals(1, bookManager.getBooks().size());
        assertEquals(2, bookManager.getBooks().get(0).getQuantity());

        // Delete → quantity decreases
        String result = bookManager.deleteBook(0);
        assertTrue(result.contains("Decreased quantity"));
        assertEquals(1, bookManager.getBooks().get(0).getQuantity());

        // Delete again → book is fully removed
        String result2 = bookManager.deleteBook(0);
        assertTrue(result2.contains("Book deleted"));
        assertEquals(0, bookManager.getBooks().size());
    }


}
