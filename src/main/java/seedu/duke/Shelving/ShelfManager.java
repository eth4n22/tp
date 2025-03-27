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

public class ShelfManager {
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


    public ShelfManager() {
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

    //delete(genre, shelfnumber, index)



}
