//@@author jenmarieng
package seedu.duke.member;

import seedu.duke.book.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library member who can borrow and return books.
 */
public class Member {
    private final String name;
    private final List<Book> borrowedBooks;

    /**
     * Constructs a Member with the specified name.
     *
     * @param name The name of the member.
     */
    public Member(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Adds a book to the list of books borrowed by the member.
     *
     * @param book The book to be borrowed.
     */
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    /**
     * Removes a book from the list of books borrowed by the member.
     *
     * @param book The book to be returned.
     */
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    /**
     * Returns a list of books currently borrowed by the member that are overdue.
     *
     * @return List of overdue books.
     */
    public List<Book> getOverdueBooks() {
        List<Book> overdueBooks = new ArrayList<>();
        for (Book book : borrowedBooks) {
            if (book.isOverdue()) {
                overdueBooks.add(book);
            }
        }
        return overdueBooks;
    }

    @Override
    public String toString() {
        return name;
    }
}
