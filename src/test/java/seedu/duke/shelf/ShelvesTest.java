package seedu.duke.shelf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import seedu.duke.Shelving.Shelves.Shelf;
import seedu.duke.Shelving.Shelves.Shelves;
import seedu.duke.book.Book;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShelvesTest {

    private Shelves shelves;

    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        shelves = new Shelves("R");

        Shelf shelf0 = new Shelf(0, "R");
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book 1", "Author A", false, LocalDate.of(2025, 4, 1)));
        books.add(new Book("Book 2", "Author B", false, LocalDate.of(2025, 5, 1)));

        Field shelfField = Shelf.class.getDeclaredField("shelfBooks");
        shelfField.setAccessible(true);
        Field booksNumField = Shelf.class.getDeclaredField("booksCurrentlyOnShelf");
        booksNumField.setAccessible(true);

        shelfField.set(shelf0, books);
        booksNumField.set(shelf0, 2);

        shelves.shelves[0] = shelf0;
    }

    @Test
    public void testDeleteBookFromSection() {
        shelves.deleteBookFromSection(0, 1); // Delete "Book 2"
        List<Book> books = shelves.shelves[0].getShelfBooks();
        int booksNum = shelves.shelves[0].getBooksCurrentlyOnShelf();
        Book book = books.get(1);
        assertEquals("duMmy", book.getTitle());
        assertEquals("duMmy", book.getAuthor());
        assertEquals(1, booksNum);
    }
    @Test
    public void test2DeleteBookFromSection() {
        shelves.deleteBookFromSection(0, 0); // Delete "Book 1"
        List<Book> books = shelves.shelves[0].getShelfBooks();
        int booksNum = shelves.shelves[0].getBooksCurrentlyOnShelf();
        Book book = books.get(0);
        assertEquals("duMmy", book.getTitle());
        assertEquals("duMmy", book.getAuthor());
        assertEquals(1, booksNum);
    }
}
