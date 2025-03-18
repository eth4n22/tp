package seedu.duke.book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Book {
    private String title;
    private String author;
    private boolean isBorrowed;
    private LocalDate returnDueDate;


    public Book(String title, String author) {
        assert title != null && !title.trim().isEmpty() : "Title cannot be empty";
        assert author != null && !author.trim().isEmpty() : "Author cannot be empty";

        this.title = title;
        this.author = author;
        this.isBorrowed = false;
        this.returnDueDate = null;
    }

    public Book(String title, String author, boolean isBorrowed) {
        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
    }

    public void setStatus(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }


    /**
     * Returns the string representation of the book.
     * @return Formatted string with book details.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String status = isBorrowed ? "[X]" : "[ ]";
        String dueDateStr = (returnDueDate != null) ? "(Due: " + returnDueDate.format(formatter) + ")" : "";
        return status + " " + title + " (by " + author + ") " + dueDateStr;
    }

    /**
     *Edit Accordingly depending on how Wayne wants to format this
     */
    public String toFileFormat() {
        return title + " | " + author + " | " + (isBorrowed ? 1 : 0) +
                (returnDueDate != null ? " | " + returnDueDate.toString() : "");
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public LocalDate getReturnDueDate() {
        return returnDueDate;
    }
}
