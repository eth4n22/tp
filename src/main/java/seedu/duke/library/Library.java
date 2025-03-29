package seedu.duke.library;

import seedu.duke.Shelving.ShelvesManager;
import seedu.duke.book.Book;
import seedu.duke.book.BookManager;
import seedu.duke.exception.BookNotFoundException;

import java.util.List;

public class Library {
    private final BookManager catalogueManager;
    private final ShelvesManager shelvesManager;

    public Library(List<Book>allBooks) {
        catalogueManager = new BookManager(allBooks);
        shelvesManager = new ShelvesManager();
    }


    public void listBooks() {
        //catelogueManager.listBooks();
    }

    public String listBorrowedBooks() {
        return catalogueManager.listBorrowedBooks();
    }

    public String addNewBook(String bookDetails, String genre) {
        return null;
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

    public String updateBookStatus(String userInput) {
        //update Book in catelogue
        //update book in shelf
        return null;
    }

    public List<Book> getBooks() {
        return catalogueManager.getBooks();
    }
}
