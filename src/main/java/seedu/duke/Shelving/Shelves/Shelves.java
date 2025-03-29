package seedu.duke.Shelving.Shelves;

import seedu.duke.exception.SectionFullException;

/**
 * Represents a collection of shelves in a library, grouped under a section identifier.
 * Each `Shelves` object manages an array of 5 {@link Shelf} objects and provides
 * operations to add, delete, and check the status of books across these shelves.
 */
public class Shelves {
    public final Shelf[] shelves;
    private boolean isFull = false;
    private final String IDENTIFIER;

    private final String NO_SECTION_AVAILABLE = "There is no section available";

    /**
     * Constructs a new `Shelves` section with the given genre Identifier.
     * Initializes 5 empty shelves labeled with the identifier and index.
     *
     * @param identifier A unique string ID for this section (e.g., "FIC").
     */
    public Shelves(String identifier) {
        this.IDENTIFIER = identifier;
        this.shelves = new Shelf[5];
        for (int shelfIndex = 0; shelfIndex < 5; shelfIndex++) {
            shelves[shelfIndex] = new Shelf(shelfIndex, IDENTIFIER);
        }
    }

    /**
     * Marks this section as full (all shelves are occupied).
     */
    private void setShelfSectionAsFull() {
        this.isFull = true;
    }

    /**
     * Checks if all shelves in this section are full.
     *
     * @return `true` if all 5 shelves are full, `false` otherwise.
     */
    private boolean checkIfShelfSectionIsFull() {
        int counter = 0;
        for (int index = 0; index < 5; index++) {
            if (shelves[index].isFull()) {
                counter++;
            }
        }
        if (counter == 5) {
            setShelfSectionAsFull();
            return true;
        }
        return false;
    }

    /**
     * Generates the full shelf number (identifier + index).
     *
     * @param index The shelf index (0–4).
     * @return The formatted shelf number.
     */
    public String getShelfNumber(int index) {
        return IDENTIFIER + String.valueOf(index);
    }

    /**
     * Adds a book to the first available shelf in this section.
     *
     * @param bookDetails The book details in "TITLE/AUTHOR" format.
     * @return The shelf number where the book was added (e.g., "FIC1").
     * @throws SectionFullException If no shelves have available slots.
     */
    public String addBookToSection(String bookDetails) {
        if (checkIfShelfSectionIsFull()) {
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
