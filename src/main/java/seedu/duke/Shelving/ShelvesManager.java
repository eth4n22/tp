package seedu.duke.Shelving;

import seedu.duke.Shelving.Shelves.ActionShelves;
import seedu.duke.Shelving.Shelves.AdventureShelves;
import seedu.duke.Shelving.Shelves.HorrorShelves;
import seedu.duke.Shelving.Shelves.MysteryShelves;
import seedu.duke.Shelving.Shelves.NonFictionShelves;
import seedu.duke.Shelving.Shelves.RomanceShelves;
import seedu.duke.Shelving.Shelves.SciFiShelves;
import seedu.duke.book.Book;
import seedu.duke.exception.SectionFullException;

public class ShelvesManager {
    private RomanceShelves romanceShelves;
    private AdventureShelves adventureShelves;
    private ActionShelves actionShelves;
    private HorrorShelves horrorShelves;
    private MysteryShelves mysteryShelves;
    private NonFictionShelves nonFictionShelves;
    private SciFiShelves sciFiShelves;

    private static final String ROMANCE = "romance";
    private static final String ADVENTURE = "adventure";
    private static final String ACTION = "action";
    private static final String HORROR = "horror";
    private static final String MYSTERY = "mystery";
    private static final String NONFICTION = "nonfiction";
    private static final String SCIFI = "scifi";

    private static final String ROMANCE_ID = "R";
    private static final String ADVENTURE_ID = "AD";
    private static final String ACTION_ID = "AC";
    private static final String HORROR_ID = "H";
    private static final String MYSTERY_ID = "MY";
    private static final String NONFICTION_ID = "NF";
    private static final String SCIFI_ID = "SCIF";

    public ShelvesManager() {
        romanceShelves = new RomanceShelves();
        adventureShelves = new AdventureShelves();
        actionShelves = new ActionShelves();
        horrorShelves = new HorrorShelves();
        mysteryShelves = new MysteryShelves();
        nonFictionShelves = new NonFictionShelves();
        sciFiShelves = new SciFiShelves();
    }
    //iterate through shelves by genre
    //if free, add to shelf
    //else throw exception

    public void addBook(Book book, String genre) {
        try {
            switch (genre) {
            case ROMANCE:
                romanceShelves.addBookToSection(book);
            case ADVENTURE:
                adventureShelves.addBookToSection(book);
            case ACTION:
                actionShelves.addBookToSection(book);
            case HORROR:
                horrorShelves.addBookToSection(book);
            case MYSTERY:
                mysteryShelves.addBookToSection(book);
            case NONFICTION:
                nonFictionShelves.addBookToSection(book);
            case SCIFI:
                sciFiShelves.addBookToSection(book);
            }
        } catch (SectionFullException e) {
            System.out.println(e);
        }
    }

    public String deleteBook(String bookID) {
        String[] parts = bookID.split("-");
        String shelfID = parts[0];
        int shelfNum = Integer.parseInt(parts[1]);
        int slotNum = Integer.parseInt(parts[2]);
        switch (shelfID) {
        case ROMANCE_ID:
            return romanceShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case ADVENTURE_ID:
            return adventureShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case ACTION_ID:
            return actionShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case HORROR_ID:
            return horrorShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case MYSTERY_ID:
            return mysteryShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case NONFICTION_ID:
            return nonFictionShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        case SCIFI_ID:
            return sciFiShelves.deleteBookFromSection(shelfNum, slotNum) + shelfNum;
        default:
            return "No such book";
        }
    }

}
