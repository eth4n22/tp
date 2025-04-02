package seedu.duke.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse; // If needed for negative checks


/**
 * JUnit test class for the BookFinder functionality.
 */
public class BookFinderTest {

    private List<Book> sampleBooks;
    private BookFinder bookFinder;

    // Sample Books - Create instances before each test
    private Book book1; // SciFi
    private Book book2; // Adventure
    private Book book3; // Adventure
    private Book book4; // Horror (with NIL ID initially)
    private Book book5; // Romance

    @BeforeEach
    void setUp() {
        sampleBooks = new ArrayList<>();

        // Initialize sample books with diverse data including different cases
        book1 = new Book("Dune", "Frank Herbert", false, null, "SCIF-0-0", null);
        book2 = new Book("The Hobbit", "J.R.R. Tolkien", true, LocalDate.now().plusDays(5), "AD-0-1", "Alice");
        book3 = new Book("The Lord of the Rings", "J.R.R. Tolkien", false, null, "AD-1-5", null);
        // Book with a NIL ID to test genre/ID searching
        book4 = new Book("Dracula", "Bram Stoker"); // Uses constructor that sets ID to "NIL"
        book5 = new Book("Pride and Prejudice", "Jane Austen", false, null, "R-0-2", null);


        sampleBooks.add(book1);
        sampleBooks.add(book2);
        sampleBooks.add(book3);
        sampleBooks.add(book4);
        sampleBooks.add(book5);

        // Create a new BookFinder instance for each test, operating on the fresh sample list
        bookFinder = new BookFinder(sampleBooks);
    }

    // --- Tests for findBooksByTitle ---

    @Test
    void findBooksByTitle_exactMatchLowerCase_shouldReturnOneBook() {
        List<Book> results = bookFinder.findBooksByTitle("dune");
        assertEquals(1, results.size());
        assertTrue(results.contains(book1));
    }

    @Test
    void findBooksByTitle_exactMatchMixedCase_shouldReturnOneBook() {
        List<Book> results = bookFinder.findBooksByTitle("The Hobbit");
        assertEquals(1, results.size());
        assertTrue(results.contains(book2));
    }

    @Test
    void findBooksByTitle_partialMatch_shouldReturnMatchingBooks() {
        List<Book> results = bookFinder.findBooksByTitle("the"); // Should match "The Hobbit", "The Lord of the Rings"
        assertEquals(2, results.size());
        assertTrue(results.contains(book2));
        assertTrue(results.contains(book3));
    }

    @Test
    void findBooksByTitle_partialMatchCaseInsensitive_shouldReturnMatchingBooks() {
        List<Book> results = bookFinder.findBooksByTitle("lOrD"); // Should match "The Lord of the Rings"
        assertEquals(1, results.size());
        assertTrue(results.contains(book3));
    }

    @Test
    void findBooksByTitle_noMatch_shouldReturnEmptyList() {
        List<Book> results = bookFinder.findBooksByTitle("Foundation");
        assertTrue(results.isEmpty());
    }

    @Test
    void findBooksByTitle_emptyQuery_shouldReturnAllBooks() {
        // The finder itself contains("") returns true, so it matches all.
        // Command layer should prevent empty search terms if needed.
        List<Book> results = bookFinder.findBooksByTitle("");
        assertEquals(sampleBooks.size(), results.size());
    }

    // --- Tests for findBooksByAuthor ---

    @Test
    void findBooksByAuthor_exactMatchMixedCase_shouldReturnOneBook() {
        List<Book> results = bookFinder.findBooksByAuthor("Frank Herbert");
        assertEquals(1, results.size());
        assertTrue(results.contains(book1));
    }

    @Test
    void findBooksByAuthor_partialMatchLowerCase_shouldReturnMultipleBooks() {
        List<Book> results = bookFinder.findBooksByAuthor("tolkien"); // Should match book2 and book3
        assertEquals(2, results.size());
        assertTrue(results.contains(book2));
        assertTrue(results.contains(book3));
    }

    @Test
    void findBooksByAuthor_partialMatchUpperCase_shouldReturnMultipleBooks() {
        List<Book> results = bookFinder.findBooksByAuthor("TOLKIEN"); // Case-insensitive check
        assertEquals(2, results.size());
        assertTrue(results.contains(book2));
        assertTrue(results.contains(book3));
    }

    @Test
    void findBooksByAuthor_noMatch_shouldReturnEmptyList() {
        List<Book> results = bookFinder.findBooksByAuthor("Asimov");
        assertTrue(results.isEmpty());
    }

    // --- Tests for findBooksByGenre ---

    @Test
    void findBooksByGenre_exactMatchLowerCase_shouldReturnOneBook() {
        List<Book> results = bookFinder.findBooksByGenre("scifi"); // book1 has ID SCIF-0-0
        assertEquals(1, results.size());
        assertTrue(results.contains(book1));
    }

    @Test
    void findBooksByGenre_exactMatchUpperCase_shouldReturnMultipleBooks() {
        List<Book> results = bookFinder.findBooksByGenre("ADVENTURE"); // book2, book3 have AD IDs
        assertEquals(2, results.size());
        assertTrue(results.contains(book2));
        assertTrue(results.contains(book3));
    }

    @Test
    void findBooksByGenre_noMatch_shouldReturnEmptyList() {
        List<Book> results = bookFinder.findBooksByGenre("mystery");
        assertTrue(results.isEmpty());
    }

    @Test
    void findBooksByGenre_unknownGenreForNilId_shouldReturnBook() {
        // book4 has ID "NIL", which getGenre() maps to "Unknown"
        List<Book> results = bookFinder.findBooksByGenre("Unknown");
        assertEquals(1, results.size());
        assertTrue(results.contains(book4));
    }

    @Test
    void findBooksByGenre_caseInsensitiveUnknown_shouldReturnBook() {
        List<Book> results = bookFinder.findBooksByGenre("uNkNoWn");
        assertEquals(1, results.size());
        assertTrue(results.contains(book4));
    }


    // --- Tests for findBooksByShelfId (Book ID) ---

    @Test
    void findBooksByShelfId_exactMatchLowerCase_shouldReturnOneBook() {
        List<Book> results = bookFinder.findBooksByShelfId("scif-0-0"); // book1
        assertEquals(1, results.size());
        assertTrue(results.contains(book1));
    }

    @Test
    void findBooksByShelfId_exactMatchUpperCase_shouldReturnOneBook() {
        List<Book> results = bookFinder.findBooksByShelfId("AD-0-1"); // book2
        assertEquals(1, results.size());
        assertTrue(results.contains(book2));
    }

    @Test
    void findBooksByShelfId_noMatch_shouldReturnEmptyList() {
        List<Book> results = bookFinder.findBooksByShelfId("NF-9-9");
        assertTrue(results.isEmpty());
    }

    @Test
    void findBooksByShelfId_matchNilId_shouldReturnBook() {
        List<Book> results = bookFinder.findBooksByShelfId("NIL"); // book4
        assertEquals(1, results.size());
        assertTrue(results.contains(book4));
    }

    @Test
    void findBooksByShelfId_caseInsensitiveNil_shouldReturnBook() {
        List<Book> results = bookFinder.findBooksByShelfId("nil"); // book4
        assertEquals(1, results.size());
        assertTrue(results.contains(book4));
    }

    @Test
    void findBooksByShelfId_bookWithNullId_shouldBeSkipped() {
        // Test that searching doesn't crash if a book somehow had a null ID (though constructor prevents this now)
        // Our setup uses "NIL" instead of null, so this specific test isn't directly applicable
        // to the current setup, but shows the intent if null IDs were possible.
        // Let's test searching for a valid ID when a NIL ID book exists.
        List<Book> results = bookFinder.findBooksByShelfId("R-0-2"); // book5
        assertEquals(1, results.size());
        assertTrue(results.contains(book5));
        assertFalse(results.contains(book4)); // Ensure the NIL ID book isn't included
    }
}
