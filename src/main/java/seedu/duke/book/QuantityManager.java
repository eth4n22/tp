package seedu.duke.book;

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
    public int getHowManyBooks(String title, String authorName) {
        return BookManager.findBookQuantity(title, authorName);
    }
}
