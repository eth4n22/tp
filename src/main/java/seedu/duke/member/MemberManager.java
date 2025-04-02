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

    public String listMembersWithOverdueBooks() {
        StringBuilder output = new StringBuilder("Members with overdue books:\n");
        boolean hasOverdueMembers = false;
        int memberIndex = 1;

        for (Member member : members) {
            List<Book> overdueBooks = member.getOverdueBooks();
            if (!overdueBooks.isEmpty()) {
                output.append(memberIndex).append(". ").append(member.getName()).append(" - ");
                for (int i = 0; i < overdueBooks.size(); i++) {
                    Book overdueBook = overdueBooks.get(i);
                    String title = overdueBook.getTitle();
                    String author = overdueBook.getAuthor();
                    output.append(title).append(" (by: ").append(author).append(")");
                    if (i < overdueBooks.size() - 1) {
                        output.append(", ");
                        
                    }
                }
                output.append("\n");
                hasOverdueMembers = true;
                memberIndex++;
            }
        }
        return hasOverdueMembers ? output.toString() : "No members have overdue books.";
    }

    public void cleanup() {
        members.clear();
    }
}
