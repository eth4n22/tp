package seedu.duke.member;

import seedu.duke.book.Book;

import java.util.ArrayList;
import java.util.List;

public class MemberManager {
    private static MemberManager memberManagerInstance;
    private final List<Member> members;

    private MemberManager() {
        this.members = new ArrayList<>();
    }

    public static MemberManager getInstance() {
        if (memberManagerInstance == null) {
            memberManagerInstance = new MemberManager();
        }
        return memberManagerInstance;
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

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
