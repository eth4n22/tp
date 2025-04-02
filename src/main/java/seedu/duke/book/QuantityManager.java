package seedu.duke.book;

import seedu.duke.exception.LeBookException;

public class QuantityManager {
    private static QuantityManager quantityManagerInstance;

    private QuantityManager() {}

    public static QuantityManager getQuantityManagerInstance() {
        if (quantityManagerInstance == null) {
            quantityManagerInstance = new QuantityManager();
        }
        return quantityManagerInstance;
    }

    //@@author jenmarieng
    public int getHowManyBorrowed(String title, String authorName) {
        return BookManager.findBookQuantityBorrowed(title, authorName);
    }

    //@@author jenmarieng
    public int getHowManyBooks(String title, String authorName) throws LeBookException {
        boolean hasMissingFields = title == null || title.trim().isEmpty() || authorName == null
                || authorName.trim().isEmpty();
        if (hasMissingFields) {
            throw new LeBookException("Both the book title and author cannot be empty.");
        }
        return BookManager.findBookQuantity(title, authorName);
    }
}
