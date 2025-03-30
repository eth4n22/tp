package seedu.duke.library;

import seedu.duke.shelving.ShelvesManager;
import seedu.duke.book.Book;
import seedu.duke.book.BookManager;

import seedu.duke.exception.BookNotFoundException;

import seedu.duke.member.MemberManager;


import java.util.List;

public class Library {
    private static Library theOneLibrary;

    private final BookManager catalogueManager;
    private final ShelvesManager shelvesManager;

    private Library(List<Book> allBooks) {
        catalogueManager = new BookManager(allBooks);
        shelvesManager = new ShelvesManager();
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
    public String listShelf(String shelfGenre, int shelfIndex) {
        return shelvesManager.listShelf(shelfGenre, shelfIndex);
    }

    public String listBorrowedBooks() {
        return catalogueManager.listBorrowedBooks();
    }

    //@@author Deanson-Choo
    public String addNewBookToCatalogue(String bookDetails, String genre) {
        String bookID = shelvesManager.getBookId(genre);
        return catalogueManager.addNewBookToCatalogue(bookDetails, bookID);
    }

    //@@author WayneCh0y
    public String addNewBookToShelf(String bookDetails, String genre) {
        return shelvesManager.addBook(bookDetails, genre);
    }

    public String deleteBook(int bookIndex){
        try {
            String bookID = catalogueManager.getBookID(bookIndex);
            String response1 = catalogueManager.deleteBook(bookIndex);
            //assuming the book exists - not a dummy
            shelvesManager.deleteBook(bookID);
            return response1;
        } catch (BookNotFoundException e) {
            return e.getMessage();
        }
    }

    public String updateBookStatus(String userInput, int bookIndex, MemberManager memberManager) {
        //update Book in catelogue
        //update book in shelf
        return null;
    }

    public List<Book> getBooks() {
        return catalogueManager.getBooks();
    }
}
