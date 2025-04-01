//package seedu.duke.shelf;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seedu.duke.shelving.shelves.RomanceShelves;
//import seedu.duke.book.Book;
//import seedu.duke.library.Library;
//import seedu.duke.shelving.shelves.Shelf;
//import seedu.duke.shelving.ShelvesManager;
//import seedu.duke.shelving.shelves.Shelves;
//
//import java.lang.reflect.Field;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
////@@author Deanson Choo
//public class LibraryTest {
//
//    private Library library;
//
//    @BeforeEach
//    public void setup() throws Exception {
//        Library.resetLibrary();
//        ShelvesManager.resetShelvesManager();
//
//        Book book = new Book("Delete Me", "Author A");
//        book.setBookID("R-0-0");
//        book.setStatus(false);
//        book.setReturnDueDate(LocalDate.of(2025, 5, 5));
//        List<Book> catalogBooks = new ArrayList<>();
//        catalogBooks.add(book);
//
//        library = Library.getTheOneLibrary(catalogBooks);
//
//        ShelvesManager shelvesManager = ShelvesManager.getShelvesManagerInstance();
//
//        RomanceShelves romanceShelves = new RomanceShelves();
//
//        Shelf shelf0 = new Shelf(0, "R");
//
//
//        Field shelfField = Shelf.class.getDeclaredField("shelfBooks");
//        shelfField.setAccessible(true);
//
//        shelfField.set(shelf0, catalogBooks);
//
//        Field countField = Shelf.class.getDeclaredField("booksCurrentlyOnShelf");
//        countField.setAccessible(true);
//        countField.set(shelf0, 1);
//
//        Field shelvesField = Shelves.class.getDeclaredField("shelves");
//        shelvesField.setAccessible(true);
//        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);
//        shelvesArray[0] = shelf0;
//
//
//        Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
//        romanceShelvesField.setAccessible(true);
//        romanceShelvesField.set(shelvesManager, romanceShelves);
//
//        Field shelvesManagerField = Library.class.getDeclaredField("shelvesManager");
//        shelvesManagerField.setAccessible(true);
//        shelvesManagerField.set(library, shelvesManager);
//    }
//
//    @SuppressWarnings("unchecked")
//    @Test
//    public void testDeleteBookByIndex_exist() throws NoSuchFieldException, IllegalAccessException {
//
//        String result = library.deleteBook(0);
//        System.out.println(result);  // For debugging output
//        assertTrue(result.contains("Now you have 0 books"), "Should confirm deletion");
//        assertTrue(result.contains("Book deleted"), "Should include catalog deletion message");
//
//        Field shelvesManagerField = Library.class.getDeclaredField("shelvesManager");
//        shelvesManagerField.setAccessible(true);
//        ShelvesManager shelvesManager = (ShelvesManager) shelvesManagerField.get(library);
//
//
//        Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
//        romanceShelvesField.setAccessible(true);
//        RomanceShelves romanceShelves = (RomanceShelves) romanceShelvesField.get(shelvesManager);
//
//        Field shelvesField = Shelves.class.getDeclaredField("shelves");
//        shelvesField.setAccessible(true);
//        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);
//        Shelf shelf0 = shelvesArray[0];
//
//        Field shelfBooksField = Shelf.class.getDeclaredField("shelfBooks");
//        shelfBooksField.setAccessible(true);
//        List<Book> booksOnShelf = (List<Book>) shelfBooksField.get(shelf0);
//
//        Book bookAfterDelete = booksOnShelf.get(0);
//        assertEquals("duMmY", bookAfterDelete.getTitle(), "Book on shelf should be dummy after deletion");
//        assertEquals("duMmY", bookAfterDelete.getAuthor(), "Author should be dummy after deletion");
//    }
//
//    @Test
//    public void testDeleteBookByBook_noExist() {
//
//        String result = library.deleteBook(1);
//        System.out.println(result);
//        assertTrue(result.contains("Book not found!"), "Book shouldn't exist");
//    }
//
//    @SuppressWarnings("unchecked")
//    @Test
//    public void testDeleteBookByBook_exist() throws NoSuchFieldException, IllegalAccessException {
//        library.addNewBookToCatalogue("Delete Me", "Author A", "romance");
//        library.addNewBookToShelf("Delete Me", "Author A", "romance");
//
//        String result = library.deleteBook("Delete Me", "Author A");
//        assertTrue(result.contains("Book deleted"), "Should include catalog deletion message");
//        assertTrue(result.contains("Now you have 0 books"), "Should confirm deletion");
//
//        Field shelvesManagerField = Library.class.getDeclaredField("shelvesManager");
//        shelvesManagerField.setAccessible(true);
//        ShelvesManager shelvesManager = (ShelvesManager) shelvesManagerField.get(library);
//
//
//        Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
//        romanceShelvesField.setAccessible(true);
//        RomanceShelves romanceShelves = (RomanceShelves) romanceShelvesField.get(shelvesManager);
//
//        Field shelvesField = Shelves.class.getDeclaredField("shelves");
//        shelvesField.setAccessible(true);
//        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);
//        Shelf shelf0 = shelvesArray[0];
//
//        Field shelfBooksField = Shelf.class.getDeclaredField("shelfBooks");
//        shelfBooksField.setAccessible(true);
//        List<Book> booksOnShelf = (List<Book>) shelfBooksField.get(shelf0);
//
//        Book bookAfterDelete = booksOnShelf.get(1);
//        assertEquals("duMmY", bookAfterDelete.getTitle(), "Book on shelf should be dummy after deletion");
//        assertEquals("duMmY", bookAfterDelete.getAuthor(), "Author should be dummy after deletion");
//    }
//
//    @Test
//    public void testDeleteBookByIndex_noExist() {
//
//        String result = library.deleteBook("hello", "Author A");
//        System.out.println(result);
//        assertTrue(result.contains("Book not found!"), "Book shouldn't exist");
//
//    }
//}
// package seedu.duke.shelf;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import seedu.duke.shelving.shelves.RomanceShelves;
// import seedu.duke.book.Book;
// import seedu.duke.library.Library;
// import seedu.duke.shelving.shelves.Shelf;
// import seedu.duke.shelving.ShelvesManager;
// import seedu.duke.shelving.shelves.Shelves;

// import java.lang.reflect.Field;
// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// //@@author Deanson Choo
// public class LibraryTest {

//     private Library library;

//     @BeforeEach
//     public void setup() throws Exception {
//         Book book = new Book("Delete Me", "Author A");
//         book.setBookID("R-0-0");
//         book.setStatus(false);
//         book.setReturnDueDate(LocalDate.of(2025, 5, 5));
//         List<Book> catalogBooks = new ArrayList<>();
//         catalogBooks.add(book);

//         library = Library.getTheOneLibrary(catalogBooks);

//         ShelvesManager shelvesManager = ShelvesManager.getShelvesManagerInstance();


//         RomanceShelves romanceShelves = new RomanceShelves();

//         Shelf shelf0 = new Shelf(0, "R");


//         Field shelfField = Shelf.class.getDeclaredField("shelfBooks");
//         shelfField.setAccessible(true);

//         shelfField.set(shelf0, catalogBooks);

//         Field countField = Shelf.class.getDeclaredField("booksCurrentlyOnShelf");
//         countField.setAccessible(true);
//         countField.set(shelf0, 1);

//         Field shelvesField = Shelves.class.getDeclaredField("shelves");
//         shelvesField.setAccessible(true);
//         Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);
//         shelvesArray[0] = shelf0;


//         Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
//         romanceShelvesField.setAccessible(true);
//         romanceShelvesField.set(shelvesManager, romanceShelves);

//         Field shelvesManagerField = Library.class.getDeclaredField("shelvesManager");
//         shelvesManagerField.setAccessible(true);
//         shelvesManagerField.set(library, shelvesManager);
//     }

//     @SuppressWarnings("unchecked")
//     @Test
//     public void testDeleteBookByIndex_exist() throws NoSuchFieldException, IllegalAccessException {
//         String result = library.deleteBook(0);
//         System.out.println(result);  // For debugging output
//         assertTrue(result.contains("Now you have 0 books"), "Should confirm deletion");
//         assertTrue(result.contains("Book deleted"), "Should include catalog deletion message");

//         Field shelvesManagerField = Library.class.getDeclaredField("shelvesManager");
//         shelvesManagerField.setAccessible(true);
//         ShelvesManager shelvesManager = (ShelvesManager) shelvesManagerField.get(library);


//         Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
//         romanceShelvesField.setAccessible(true);
//         RomanceShelves romanceShelves = (RomanceShelves) romanceShelvesField.get(shelvesManager);

//         Field shelvesField = Shelves.class.getDeclaredField("shelves");
//         shelvesField.setAccessible(true);
//         Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);
//         Shelf shelf0 = shelvesArray[0];

//         Field shelfBooksField = Shelf.class.getDeclaredField("shelfBooks");
//         shelfBooksField.setAccessible(true);
//         List<Book> booksOnShelf = (List<Book>) shelfBooksField.get(shelf0);

//         Book bookAfterDelete = booksOnShelf.get(0);
//         assertEquals("duMmY", bookAfterDelete.getTitle(), "Book on shelf should be dummy after deletion");
//         assertEquals("duMmY", bookAfterDelete.getAuthor(), "Author should be dummy after deletion");
//     }

//     @Test
//     public void testDeleteBookByBook_noExist() throws Exception {
//         String result = library.deleteBook(1);
//         System.out.println(result);
//         assertTrue(result.contains("Invalid book index"), "Book shouldn't exist");
//     }

//     @SuppressWarnings("unchecked")
//     @Test
//     public void testDeleteBookByBook_exist() throws NoSuchFieldException, IllegalAccessException {
//         library.addNewBookToCatalogue("Delete Me", "Author A", "romance");
//         library.addNewBookToShelf("Delete Me", "Author A", "romance");

//         String result = library.deleteBook("Delete Me", "Author A");
//         assertTrue(result.contains("Book deleted"), "Should include catalog deletion message");
//         assertTrue(result.contains("Now you have 0 books"), "Should confirm deletion");

//         Field shelvesManagerField = Library.class.getDeclaredField("shelvesManager");
//         shelvesManagerField.setAccessible(true);
//         ShelvesManager shelvesManager = (ShelvesManager) shelvesManagerField.get(library);


//         Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
//         romanceShelvesField.setAccessible(true);
//         RomanceShelves romanceShelves = (RomanceShelves) romanceShelvesField.get(shelvesManager);

//         Field shelvesField = Shelves.class.getDeclaredField("shelves");
//         shelvesField.setAccessible(true);
//         Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);
//         Shelf shelf0 = shelvesArray[0];

//         Field shelfBooksField = Shelf.class.getDeclaredField("shelfBooks");
//         shelfBooksField.setAccessible(true);
//         List<Book> booksOnShelf = (List<Book>) shelfBooksField.get(shelf0);

//         Book bookAfterDelete = booksOnShelf.get(1);
//         assertEquals("duMmY", bookAfterDelete.getTitle(), "Book on shelf should be dummy after deletion");
//         assertEquals("duMmY", bookAfterDelete.getAuthor(), "Author should be dummy after deletion");
//     }

//     @Test
//     public void testDeleteBookByIndex_noExist() {
//         String result = library.deleteBook("hello", "Author A");
//         System.out.println(result);
//         assertTrue(result.contains("Book not found with title"), "Book shouldn't exist");

//     }
// }


