package seedu.duke.Shelving.Shelves;

import seedu.duke.book.Book;
import seedu.duke.book.ShelfBookManager;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
    private List<Book> shelfBooks = new ArrayList<>();
    private final int shelfIndex;
    private final String shelfGenre;

    private int booksCurrentlyOnShelf = 0;
    private final int MAX_BOOKS_ON_SHELF = 100;

    private boolean isShelfFull;

    private void setShelfAsFull() {
        isShelfFull = true;
    }

    private String getShelfIdentifier(int bookIndex) {
        return shelfGenre + "-" + shelfIndex + "-" + bookIndex;
    }

    public Shelf(int shelfIndex, String shelfGenre) {
        this.shelfIndex = shelfIndex;
        this.shelfGenre = shelfGenre;
    }

    public boolean isFull() {
        return isShelfFull;
    }

    public int getBooksCurrentlyOnShelf() {
        return booksCurrentlyOnShelf;
    }

    public String addBookToShelf(String bookDetails, int bookIndex) {
        if (isShelfFull) {
            return "The shelf is full!";
        }

        assert bookDetails != null : "Book details cannot be null";

        String[] parts = bookDetails.split(" / ", 2);

        if (parts.length < 2) {
            return "Invalid book format! It should be 'TITLE / AUTHOR'.";
        }

        String title = parts[0].trim();
        String author = parts[1].trim();

        if (title.isEmpty()) {
            return "Book title cannot be empty!";
        }

        if (author.isEmpty()) {
            return "Book author cannot be empty!";
        }

        Book newBook = new Book(title, author);
        String identifyer = getShelfIdentifier(bookIndex);
        newBook.setBookID(identifyer)
        ;
        this.shelfBooks.add(newBook);
        booksCurrentlyOnShelf += 1;

        if (booksCurrentlyOnShelf == MAX_BOOKS_ON_SHELF) {
            setShelfAsFull();
        }

        return "\nNow you have " + booksCurrentlyOnShelf + " books in the Shelf.";
    }

    public String deleteBookFromShelf(int slotNum) {
        return "hello fix this deanson";//shelfBookManager.deleteBook(slotNum);
    }

}
