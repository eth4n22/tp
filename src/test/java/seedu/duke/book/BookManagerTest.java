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
        bookManager = BookManager.getBookManagerInstance(new ArrayList<>());
    }

    @Test
    public void testAddNewBook_validFormat() {
        bookManager.cleanup();
        String result = bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald",
                "romance", "R-0-0");

        assertEquals(1, bookManager.getBooks().size());
        assertTrue(result.contains("I've added:"));
        assertTrue(result.contains("The Great Gatsby"));
        assertTrue(result.contains("F. Scott Fitzgerald"));
    }

    @Test
    public void testAddNewBook_emptyTitle() {
        bookManager.cleanup();
        String result = bookManager.addNewBookToCatalogue("", "Some Author", "romance",
                "R-0-0");

        assertEquals(0, bookManager.getBooks().size());
        assertEquals("Book title cannot be empty!", result);
    }

    @Test
    public void testAddNewBook_emptyAuthor() {
        bookManager.cleanup();
        String result = bookManager.addNewBookToCatalogue("Some Title", "", "romance",
                "R-0-0");

        assertEquals(0, bookManager.getBooks().size());
        assertEquals("Book author cannot be empty!", result);
    }

    @Test
    public void testDeleteBook_validIndex() {
        bookManager.cleanup();
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
        bookManager.cleanup();
        String action = bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald",
                "romance", "R-0-0");

        String result = bookManager.deleteBook(2); // Index out of bounds

        assertEquals(1, bookManager.getBooks().size());
        assertEquals("Invalid book index provided. There is no book at index 3.", result);
    }

    @Test
    public void testDeleteBook_negativeIndex() {
        bookManager.cleanup();
        String result = bookManager.deleteBook(-1);

        assertEquals("Invalid book index provided. There is no book at index 0.", result);
    }

    @Test
    public void testListBooks_emptyLibrary() {
        bookManager.cleanup();
        String result = bookManager.listBooks();

        assertEquals("No books in the library yet.", result);
    }

    @Test
    public void testListBooks_withBooks() {
        bookManager.cleanup();
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
        bookManager.cleanup();
        String action = bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald",
                "romance", "R-0-0");
        MemberManager memberManager = MemberManager.getInstance();
        Member borrower = memberManager.getMemberByName("John");

        String result = bookManager.updateBookStatus("borrow", 0, "John", memberManager);

        // Assert message contains expected information
        assertTrue(result.contains("John has borrowed: \"The Great Gatsby\""));

        //due date is 14 days from current date rather than hard coded
        LocalDate expectedDueDate = LocalDate.now().plusDays(14);
        LocalDate actualDueDate = bookManager.getBooks().get(0).getReturnDueDate();
        assertEquals(expectedDueDate, actualDueDate);

        assertTrue(bookManager.getBooks().get(0).isBorrowed());
    }




    @Test
    public void testUpdateBookStatus_return() {
        bookManager.cleanup();
        bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald",
                "romance", "R-0-0");
        MemberManager memberManager = MemberManager.getInstance();
        bookManager.updateBookStatus("borrow", 0, "John", memberManager);

        String result = bookManager.updateBookStatus("return", 0, "John",
                memberManager);

        assertEquals("Returned: \"The Great Gatsby\"", result);
        assertFalse(bookManager.getBooks().get(0).isBorrowed());
    }

    @Test
    public void testUpdateBookStatus_invalidIndex() {
        bookManager.cleanup();
        bookManager.addNewBookToCatalogue("Test Book", "Test Author", "romance", "R-0-0");
        MemberManager memberManager = MemberManager.getInstance();

        String result = bookManager.updateBookStatus("borrow", 2, "John",
                memberManager);

        assertEquals("Invalid book index provided. There is no book at index 3.", result);
    }

    @Test
    void testBorrowBook() {
        bookManager.cleanup();
        bookManager.addNewBookToCatalogue("Percy Jackson", "Rick", "romance", "R-0-0");
        bookManager.addNewBookToCatalogue("1234", "abcd", "romance", "R-0-1");
        MemberManager memberManager = MemberManager.getInstance();

        String result = bookManager.updateBookStatus("borrow", 0, "John", memberManager);

        assertTrue(result.contains("John has borrowed: \"Percy Jackson\""));

        LocalDate expectedDueDate = LocalDate.now().plusDays(14);
        LocalDate actualDueDate = bookManager.getBooks().get(0).getReturnDueDate();
        assertEquals(expectedDueDate, actualDueDate);

        assertTrue(bookManager.getBooks().get(0).isBorrowed());
    }



    @Test
    void testReturnBook() {
        bookManager.cleanup();
        bookManager.addNewBookToCatalogue("Harry Potter", "Wayne", "romance", "R-0-0");
        bookManager.addNewBookToCatalogue("I Love 2113", "Deanson", "romance", "R-0-1");
        MemberManager memberManager = MemberManager.getInstance();
        bookManager.updateBookStatus("borrow", 0, "John", memberManager);

        String result = bookManager.updateBookStatus("return", 0, "John",
                memberManager);

        assertEquals("Returned: \"Harry Potter\"", result);

        assertFalse(bookManager.getBooks().get(0).isBorrowed());
    }

    @Test
    void testInvalidBookNumber() {
        bookManager.cleanup();
        bookManager.addNewBookToCatalogue("Harry Potter", "Wayne", "romance", "R-0-0");
        bookManager.addNewBookToCatalogue("I Love 2113", "Deanson", "romance", "R-0-1");
        MemberManager memberManager = MemberManager.getInstance();
        String result = bookManager.updateBookStatus("borrow", 100, "John",
                memberManager);

        assertEquals("Invalid book index provided. There is no book at index 101.", result);
    }

    @Test
    void testAlreadyBorrowed() {
        bookManager.cleanup();
        bookManager.addNewBookToCatalogue("Harry Potter", "Wayne", "romance", "R-0-0");
        MemberManager memberManager = MemberManager.getInstance();
        bookManager.updateBookStatus("borrow", 0, "John", memberManager);

        String result = bookManager.updateBookStatus("borrow", 0, "Jane",
                memberManager);

        assertEquals("\"Harry Potter\" is already borrowed by John.", result);
    }

    @Test
    void testNotBorrowed() {
        bookManager.cleanup();
        bookManager.addNewBookToCatalogue("Harry Potter", "Wayne", "romance", "R-0-0");
        MemberManager memberManager = MemberManager.getInstance();

        String result = bookManager.updateBookStatus("return", 0, "John",
                memberManager);

        assertEquals("\"Harry Potter\" is not currently borrowed.", result);
    }

    @Test
    void testListOverdueBooks() {
        bookManager.cleanup();
        bookManager.addNewBookToCatalogue("Overdue Book","Wayne", "romance", "R-0-0");
        MemberManager memberManager = MemberManager.getInstance();
        bookManager.updateBookStatus("borrow", 0, "John", memberManager);
        bookManager.getBooks().get(0).setReturnDueDate(LocalDate.now().minusDays(2));

        String result = bookManager.listOverdueBooks();
        assertTrue(result.contains("Overdue Book"));
    }

    //@@author eth4n22
    @Test
    void testStatistics() {
        bookManager.cleanup();
        bookManager.addNewBookToCatalogue("Moby A", "Author A", "romance", "R-0-0");
        bookManager.addNewBookToCatalogue("Moby B", "Author B", "romance", "R-0-1");
        MemberManager memberManager = MemberManager.getInstance();
        bookManager.updateBookStatus("borrow", 0, "John", memberManager);

        bookManager.getBooks().get(0).setReturnDueDate(LocalDate.now().minusDays(2));

        String stats = bookManager.getStatistics();
        assertTrue(stats.contains("Total books copies: 2"));
        assertTrue(stats.contains("Unique titles: 2"));
        assertTrue(stats.contains("Total books borrowed: 1"));
        assertTrue(stats.contains("Total books overdue: 1"));
    }

    //@@author eth4n22
    @Test
    void testAddNewBookIncreaseQuantity() {
        bookManager.cleanup();
        // Add a book
        String result1 = bookManager.addNewBookToCatalogue("The Great Gatsby",
                "F. Scott Fitzgerald", "romance", "R-0-0");
        assertEquals(1, bookManager.getBooks().size());
        assertTrue(result1.contains("I've added:"));

        // Add the same book again (should increase quantity, not add new book)
        String result2 = bookManager.addNewBookToCatalogue("The Great Gatsby",
                "F. Scott Fitzgerald", "romance", "R-0-0");
        assertEquals(2, bookManager.getBooks().size()); // Still only one entry
        assertTrue(result2.contains("I've added:"));
        assertEquals(2, QuantityManager.getQuantityManagerInstance().getHowManyBooks("The Great Gatsby",
                "F. Scott Fitzgerald"));
    }

    //@@author eth4n22
    @Test
    void testDeleteBookDecreaseQuantity() {
        bookManager.cleanup();
        // Add book twice → quantity becomes 2
        bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald", "romance", "R-0-0");
        bookManager.addNewBookToCatalogue("The Great Gatsby", "F. Scott Fitzgerald", "romance", "R-0-0");

        assertEquals(2, bookManager.getBooks().size());
        assertEquals(2, QuantityManager.getQuantityManagerInstance().getHowManyBooks("The Great Gatsby",
                "F. Scott Fitzgerald"));

        // Delete → quantity decreases
        String result = bookManager.deleteBook(0);
        assertTrue(result.contains("Book deleted:"));
        assertEquals(1, QuantityManager.getQuantityManagerInstance().getHowManyBooks("The Great Gatsby",
                "F. Scott Fitzgerald"));

        // Delete again → book is fully removed
        String result2 = bookManager.deleteBook(0);
        assertTrue(result2.contains("Book deleted:"));
        assertEquals(0, QuantityManager.getQuantityManagerInstance().getHowManyBooks("The Great Gatsby",
                "F. Scott Fitzgerald"));
    }
}
