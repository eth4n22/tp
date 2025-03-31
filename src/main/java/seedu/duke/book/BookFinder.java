package seedu.duke.book;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Responsible for searching books within a provided list based on various criteria.
 * This class operates on a list of books managed elsewhere (e.g., by BookManager).
 */
public class BookFinder {

    private final List<Book> booksToSearch; // Reference to the list managed by BookManager

    /**
     * Creates a BookFinder that operates on the given list of books.
     *
     * @param booksToSearch The list of books to perform searches on. This should be
     *                      the list maintained by the BookManager.
     */
    public BookFinder(List<Book> booksToSearch) {
        // We are passing the reference. The finder operates on the *live* list from BookManager.
        this.booksToSearch = booksToSearch;
    }

    /**
     * Finds books where the title contains the given query (case-insensitive).
     *
     * @param titleQuery The title (or part of it) to search for.
     * @return A list of matching books.
     */
    public List<Book> findBooksByTitle(String titleQuery) {
        String lowerCaseQuery = titleQuery.toLowerCase();
        return booksToSearch.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }

    /**
     * Finds books where the author contains the given query (case-insensitive).
     *
     * @param authorQuery The author (or part of it) to search for.
     * @return A list of matching books.
     */
    public List<Book> findBooksByAuthor(String authorQuery) {
        String lowerCaseQuery = authorQuery.toLowerCase();
        return booksToSearch.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }

    /**
     * Finds books matching the given genre (case-insensitive, exact match).
     * Uses the Book's getGenre() method.
     *
     * @param genreQuery The genre to search for (e.g., "adventure").
     *                   Assumes the genre has been pre-validated.
     * @return A list of matching books.
     */
    public List<Book> findBooksByGenre(String genreQuery) {
        return booksToSearch.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genreQuery))
                .collect(Collectors.toList());
    }

    /**
     * Finds books located on the specified shelf ID (case-insensitive, exact match).
     *
     * @param shelfIdQuery The shelf ID (e.g., "AD-0-0") to search for.
     * @return A list of matching books.
     */
    public List<Book> findBooksByShelfId(String shelfIdQuery) {
        return booksToSearch.stream()
                .filter(book -> book.getBookID() != null && book.getBookID().equalsIgnoreCase(shelfIdQuery))
                .collect(Collectors.toList());
    }
}
