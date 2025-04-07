//@@author jenmarieng
package seedu.duke.member;

import seedu.duke.book.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that manages the list of library members.
 * Provides functionality to retrieve members by name, list overdue books of members, and manage the member list.
 */
public class MemberManager {
    private static MemberManager memberManagerInstance;
    private final List<Member> members;

    /**
     * Private constructor to enforce singleton pattern.
     * Initialises an empty list of members.
     */
    private MemberManager() {
        this.members = new ArrayList<>();
    }

    /**
     * Retrieves the singleton instance of {@code MemberManager}.
     * If it doesn't exist, it is created.
     *
     * @return The singleton instance of {@code MemberManager}.
     */
    public static MemberManager getInstance() {
        if (memberManagerInstance == null) {
            memberManagerInstance = new MemberManager();
        }
        return memberManagerInstance;
    }

    /**
     * Returns a copy of the list of all registered members.
     *
     * @return A list of all members.
     */
    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    /**
     * Retrieves a member by name from the list of members.
     * If the member does not exist, a new member is created and added to the list.
     *
     * @param name The name of the member to retrieve.
     * @return The existing or newly created member.
     */
    public Member getMemberByName(String name) {
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        Member newMember = new Member(name);
        members.add(newMember);
        return newMember;
    }

    /**
     * Builds a formatted list of a member's overdue books.
     *
     * @param overdueBooks List of overdue books.
     * @return A string listing the overdue books.
     */
    private String buildOverdueBookList(List<Book> overdueBooks) {
        StringBuilder bookListBuilder = new StringBuilder();
        for (int i = 0; i < overdueBooks.size(); i++) {
            Book overdueBook = overdueBooks.get(i);
            String title = overdueBook.getTitle();
            String author = overdueBook.getAuthor();

            bookListBuilder.append(String.format("%s (by %s)", title, author));

            if (i < overdueBooks.size() - 1) {
                bookListBuilder.append(", ");
            }
        }
        return bookListBuilder.toString();
    }

    /**
     * Lists members who have overdue books.
     *
     * @return A formatted string listing members with overdue books.
     */
    public String listMembersWithOverdueBooks() {
        StringBuilder resultBuilder = new StringBuilder("Members with overdue books:\n");
        boolean hasOverdueMembers = false;
        int memberIndex = 1;

        for (Member member : members) {
            List<Book> overdueBooks = member.getOverdueBooks();
            if (!overdueBooks.isEmpty()) {
                resultBuilder.append(memberIndex).append(". ").append(member.getName()).append(" - ");
                resultBuilder.append(buildOverdueBookList(overdueBooks));
                resultBuilder.append("\n");
                hasOverdueMembers = true;
                memberIndex++;
            }
        }
        return hasOverdueMembers ? resultBuilder.toString() : "No members have overdue books.";
    }

    public void cleanup() {
        members.clear();
    }
}
