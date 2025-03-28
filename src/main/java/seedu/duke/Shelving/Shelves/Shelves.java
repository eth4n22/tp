package seedu.duke.Shelving.Shelves;

import seedu.duke.book.Book;
import seedu.duke.exception.SectionFullException;

public class Shelves {
    public final Shelf[] shelves;
    private boolean isFull = false;
    private final String IDENTIFIER;


    public Shelves(String identifier) {
        this.IDENTIFIER = identifier;
        this.shelves = new Shelf[5];
        for (int shelfIndex = 0; shelfIndex < 5; shelfIndex++) {
            shelves[shelfIndex] = new Shelf(shelfIndex, IDENTIFIER);
        }
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

    public void addBookToSection(String bookDetails) {
        if (isFull) {
            throw new SectionFullException("No more books can be added to this section!");
        }
        for (int index = 0; index < 5; index++) {
            if (!shelves[index].isFull()) {
                shelves[index].addBookToShelf(bookDetails, index);
            }
        }
    }
    public String deleteBookFromSection(int shelfNum, int slotNum) {
        return shelves[shelfNum].deleteBookFromShelf(slotNum) + IDENTIFIER;
    }
}
