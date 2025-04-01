# LeBook User Guide

## Introduction
LeBook is a straightforward book management application that runs on the command line. With its clean interface and simple commands, LeBook helps you keep track of your personal book collection, including which books you've borrowed, lent out, or want to find quickly.

## Quick Start
1. Ensure you have **Java 17 or later** installed.
2. Download the latest **LeBook.jar** from the releases page.
3. Place the `.jar` file in an empty folder (it will create a `data` subfolder for storage).
4. Run the application using:
   ```sh
   java -jar LeBook.jar
   ```

## Features

### 1. Adding a Book
Add new books to your library with title, author, and genre information.

**Format:**
```
add TITLE / AUTHOR / GENRE
```
**Example:**
```
add The Great Gatsby / F. Scott Fitzgerald / nonfiction
```
**Example Output:**
```
____________________________________________________________
I've added: "The Great Gatsby" by F. Scott Fitzgerald (Genre: nonfiction, ID: NF-0-0).
Total unique titles in library: 1
____________________________________________________________
```

### 2. Deleting a Book
Remove books from your library. You can delete by the book's number in the list or by its title and author.

**Format 1 (By Index):**
```
delete i/BOOK_NUMBER
```
**Example:**
```
delete i/1
```

**Format 2 (By Title/Author):**
```
delete b/TITLE / AUTHOR
```
**Example:**
```
delete b/The Great Gatsby / F. Scott Fitzgerald
```

**Example Output:**
```
____________________________________________________________
Book deleted:
  [ ] The Great Gatsby by F. Scott Fitzgerald (Genre: nonfiction, ID: NF-0-0)
Now you have 0 books in the library
____________________________________________________________
```

### 3. Listing Books
View all the books currently in your library, including their status, title, author, genre, and ID.

**Format:**
```
list
```
**Example Output:**
```
____________________________________________________________
Here are the books in your library:
1. [ ] To Kill a Mockingbird by Harper Lee (Genre: nonfiction, ID: NF-0-1)
2. [ ] 1984 by George Orwell (Genre: scifi, ID: SCIF-0-0)
3. [X] The Hobbit by J.R.R. Tolkien (Genre: adventure, ID: AD-0-0) | (Due: ...) Borrowed by: Alice
Total books: 3
____________________________________________________________
```

### 4. Borrowing a Book
Mark a book as borrowed by a specific member.

**Format:**
```
borrow BOOK_NUMBER / MEMBER_NAME
```
**Example:**
```
borrow 2 / Bob
```

**Example Output:**
```
____________________________________________________________
Bob has borrowed: "1984" (Due: ...)
____________________________________________________________
```

### 5. Returning a Book
Mark a borrowed book as returned.

**Format:**
```
return BOOK_NUMBER
```
**Example:**
```
return 3
```

**Example Output:**
```
____________________________________________________________
Returned: "The Hobbit"
____________________________________________________________
```

### 6. Finding Books
Search for specific books in your library based on different criteria.

**Format:**
```
find CRITERIA SEARCH_TERM
```
**Valid Criteria:**
- `title`: Finds books whose title contains the search term (case-insensitive).
- `author`: Finds books whose author contains the search term (case-insensitive).
- `genre`: Finds books matching the specified genre (case-insensitive). Supported genres: romance, adventure, action, horror, mystery, nonfiction, scifi.
- `shelf`: Finds the book with the exact unique Book ID (e.g., AD-0-1, case-insensitive).

**Examples:**
```
find title hobbit
find author Tolkien
find genre adventure
find shelf AD-0-0
```

**Example Output (Found):**
```
____________________________________________________________
Found 1 book(s) with titles containing 'hobbit':
1. [X] The Hobbit by J.R.R. Tolkien (Genre: adventure, ID: AD-0-0) | (Due: ...) Borrowed by: Alice
____________________________________________________________
```

**Example Output (Not Found):**
```
____________________________________________________________
Sorry, no books found with titles containing 'rings'.
____________________________________________________________
```

### 7. Exiting the Application
Close the application and save the current state of your library.

**Format:**
```
bye
```
**Example Output:**
```
____________________________________________________________
Goodbye! Hope to see you again soon!
____________________________________________________________
```

## Book Status and Format
In the list and search results, books are displayed with their status and details:
- `[ ]` indicates an **available** book.
- `[X]` indicates a **borrowed** book.

**Example format:**
```
INDEX. [STATUS] TITLE by AUTHOR (Genre: GENRE, ID: BOOK_ID) | (Due: DUE_DATE) Borrowed by: BORROWER_NAME
```
(Borrower info only shown if borrowed.)

**Examples:**
```
[ ] Pride and Prejudice by Jane Austen (Genre: romance, ID: R-1-2) - Available book
[X] The Lord of the Rings by J.R.R. Tolkien (Genre: adventure, ID: AD-0-5) | (Due: 2024-12-25) Borrowed by: Charlie - Borrowed book
```

## Command Summary
| Command | Format | Example |
|---------|--------|---------|
| **Add Book** | `add TITLE / AUTHOR / GENRE` | `add Dune / Frank Herbert / scifi` |
| **Delete by Index** | `delete i/BOOK_NUMBER` | `delete i/1` |
| **Delete by Book** | `delete b/TITLE / AUTHOR` | `delete b/Dune / Frank Herbert` |
| **List Books** | `list` | `list` |
| **Borrow Book** | `borrow BOOK_NUMBER / MEMBER` | `borrow 2 / Eve` |
| **Return Book** | `return BOOK_NUMBER` | `return 3` |
| **Find Book** | `find CRITERIA TERM` | `find title dune` |
| **Exit** | `bye` | `bye` |

---


