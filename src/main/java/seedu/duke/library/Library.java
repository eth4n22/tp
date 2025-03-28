package seedu.duke.library;

import seedu.duke.book.Book;
import seedu.duke.book.BookManager;

import java.util.List;

public class Library {
    private BookManager catelogueManager;
    //private ShelvesManager shelvesManager;

    public Library(List<Book>allBooks) {
        catelogueManager = new BookManager(allBooks);
        //shelvesManager = new ShelvesManager();
    }


    public void listBooks() {
        //catelogueManager.listBooks();

    }

    public String listBorrowedBooks() {
        return catelogueManager.listBorrowedBooks();
    }

    public String addNewBook(String bookDetails, String genre) {
        return null;
    }

    public String deleteBook(int bookIndex) {
        String bookID = catelogueManager.getBookID(bookIndex);
        String response1 = catelogueManager.deleteBook(bookIndex);
        String response2 = "Dont know what is wrong here";//shelvesManager.deleteBook(bookID);
        return response1 + System.lineSeparator() + response2;
    }

    public String updateBookStatus(String userInput) {
        //update Book in catelogue
        //update book in shelf
        return null;
    }

    public List<Book> getBooks() {
        return null;
    }
}
