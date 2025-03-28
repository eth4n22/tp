package seedu.duke.Shelving.Shelves;

import seedu.duke.book.Book;
import seedu.duke.exception.SectionFullException;

public class Shelves {
    private final Shelf[] shelves = new Shelf[5];
    private boolean isFull = false;
    private final String IDENTIFIER;

    public Shelves(String identifier) {
        this.IDENTIFIER = identifier;
    }

    private void setShelfSectionAsFull() {
        this.isFull = true;
    }

    public boolean isShelfSectionFull() {
        return this.isFull;
    }

    public String getShelfNumber(int index) {
        return IDENTIFIER + String.valueOf(index);
    }

    public void addBookToSection(Book book) {
        if (isFull) {
            throw new SectionFullException("No more books can be added to this section!");
        }
        for (int index = 0; index < 5; index++) {
            if (!shelves[index].isFull()) {
                String shelfNumber = getShelfNumber(index);
                shelves[index].addBookToShelf(shelfNumber, book);
            }
        }
    }
    public void deleteBookFromSection(int shelfNum) {

    }
}
