package seedu.duke.Shelving.Shelves;

import seedu.duke.book.Book;
import seedu.duke.exception.SectionFullException;

public class Shelves {
    public final Shelf[] shelves;
    private boolean isFull = false;
    private final String IDENTIFIER;

    private final String NO_SECTION_AVAILABLE = "There is no section available";

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

    public String addBookToSection(String bookDetails) {
        if (isFull) {
            throw new SectionFullException("No more books can be added to this section!");
        }
        for (int index = 0; index < 5; index++) {
            if (!shelves[index].isFull()) {
                return shelves[index].addBookToShelf(bookDetails);
            }
        }
        return NO_SECTION_AVAILABLE;
    }
    public String deleteBookFromSection(int shelfNum, int slotNum) {
        return shelves[shelfNum].deleteBookFromShelf(slotNum) + IDENTIFIER;
    }
}
