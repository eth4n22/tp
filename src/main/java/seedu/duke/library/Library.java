package seedu.duke.library;

import seedu.duke.book.Book;
import seedu.duke.book.BookManager;
import seedu.duke.commands.Commands;
import seedu.duke.storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private BookManager catelogueManager;
    private ShelfManager shelfManager;

    public Library(List<Book>allBooks, List<List<Book>> ShelfBooks) {
        catelogueManager = new BookManager(allBooks);
        shelfManager = new ShelfManager(ShelfBooks);
    }


    public List<Book> listBooks() {
        return catelogueManager.getBooks();
    }


    public String addNewBook(String bookDetails) {
        assert bookDetails != null : "Book details cannot be null";
        catelogueManager.addNewBook(bookDetails);
        shelfManager.addBookToShelf(bookDetails);
    }

    public String deleteBook(String index) {

        catelogueManager.deleteBook(index);
        String bookDetails = catelogueManager.returnBookDetails(index);
        String genre = bookDetails.split(" ")[0];
        shelfManager.remove(genre, bookDetails);
    }

    public String updateBookStatus(String userInput) {
        //update Book in catelogue
        //update book in shelf
    }

}
