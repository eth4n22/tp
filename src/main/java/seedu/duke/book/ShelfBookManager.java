package seedu.duke.book;

import java.util.List;

public class ShelfBookManager extends BookManager
{
    public ShelfBookManager(List<Book> books) {
        super(books);
    }

    @Override
    public String deleteBook(int bookIndex) {

        String result = super.deleteBook(bookIndex);
        result = result.replace("library", "shelf: ");
        int startIndex = result.indexOf("Now");
        result = result.substring(startIndex);
        return result;

    }

}
