package seedu.duke.Shelving.Shelves;

import seedu.duke.book.Book;
import seedu.duke.book.ShelfBookManager;

public class Shelf {
    public ShelfBookManager shelfBookManager;
    boolean checker;
    private int booksCurrentlyOnShelf = 0;
    private final int MAX_BOOKS_ON_SHELF = 100;

    public boolean isFull() {
        return true;
    }

    public void addBookToShelf(String shelfNumber, Book book) {
        if (booksCurrentlyOnShelf == MAX_BOOKS_ON_SHELF) {
            return;
        }

        // Add the book
        //bookManager.addNewBook(book);
        booksCurrentlyOnShelf += 1;
    }

    public String deleteBookFromShelf(int slotNum) {
        return shelfBookManager.deleteBook(slotNum);
    }
}
