package seedu.duke.commands;

import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Command to display library statistics.
 */
public class StatisticsCommand extends Command {

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        String statistics = library.getStatistics();
        ui.printWithSeparator(statistics);
    }
}
