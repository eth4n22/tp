package seedu.duke.library;

import seedu.duke.shelving.ShelvesManager;
import seedu.duke.book.Book;
import seedu.duke.book.BookManager;

import seedu.duke.exception.BookNotFoundException;

import seedu.duke.member.MemberManager;
import seedu.duke.utility.GroupReturns;


import java.util.List;

public class Library {
    private static Library theOneLibrary;

    private final BookManager catalogueManager;
    private final ShelvesManager shelvesManager;

    private Library(List<Book> allBooks) {
        catalogueManager = BookManager.getBookManagerInstance(allBooks);
        shelvesManager = ShelvesManager.getShelvesManagerInstance();
    }

    public static Library getTheOneLibrary(List<Book> allBooks) {
        if (theOneLibrary == null) {
            theOneLibrary = new Library(allBooks);
        }
        return theOneLibrary;
    }

    public String listBooks() {
        return catalogueManager.listBooks();
    }

    //@@author WayneCh0y
    public String listShelves(String shelfGenre, int index) {
        return shelvesManager.listShelf(shelfGenre, index);
    }

    public String listBorrowedBooks() {
        return catalogueManager.listBorrowedBooks();
    }

    //@@author Deanson-Choo
    public String addNewBookToCatalogue(String title, String author, String genre) {
        String bookID = shelvesManager.getBookId(genre);
        return catalogueManager.addNewBookToCatalogue(title, author, genre, bookID);
    }

    //@@author WayneCh0y
    public String addNewBookToShelf(String title, String author, String genre) {
        return shelvesManager.addBook(title, author, genre);
    }

    //@@author Deanson Choo
    /**
     * Deletes a book from the catalogue and the corresponding shelf based on its index in the catalogue.
     * <p>
     * This method first retrieves the book ID using the given index, then removes it from both the
     * catalogue and the shelves. The book in the shelf is replaced with a dummy book.
     *
     * @param bookIndex The index of the book to delete in the catalogue.
     * @return A confirmation message if deletion is successful, or an error message if the book is not found.
     */
    public String deleteBook(int bookIndex){
        try {
            String bookID = catalogueManager.getBookID(bookIndex); //throw BookNotFound
            String response1 = catalogueManager.deleteBook(bookIndex);
            assert bookID != null; //that means it was fetchable
            shelvesManager.deleteBook(bookID);
            return response1;
        } catch (BookNotFoundException e) {
            return e.getMessage();
        }
    }
    //Delete book by Bookdetails
    public String deleteBook(String bookTitle, String author) {
        try {
            GroupReturns bookIndexBookID = catalogueManager.getBookID(bookTitle, author); //throw BookNotFound
            int bookIndex = bookIndexBookID.number;
            String bookID = bookIndexBookID.text;
            String response1 = catalogueManager.deleteBook(bookIndex);
            assert bookID != null; //that means it was fetchable
            shelvesManager.deleteBook(bookID);
            return response1;
        } catch (BookNotFoundException e) {
            return e.getMessage();
        }
    }

    public String updateBookStatus(String command, int bookIndex, String borrowerName, MemberManager memberManager) {
        return catalogueManager.updateBookStatus(command, bookIndex, borrowerName, memberManager);
    }

    //@@author Deanson-Choo
    /**
     * Returns a list of all books currently in the catalogue.
     *
     * @return A list of {@link Book} objects in the catalogue.
     */
    public List<Book> getBooks() {
        return catalogueManager.getBooks();
    }

    //    public String getStatistics() {
    //        return catalogueManager.getStatistics();
    //    }

    public String listOverdueBooks() {
        return catalogueManager.listOverdueBooks();
    }
}
