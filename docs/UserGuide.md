# LeBook User Guide

## Introduction
LeBook is a straightforward book management application that runs on the command line. With its clean interface and simple commands, LeBook helps librarians keep track of their catalogue of books as well as the shelves they are on. Some functionalities include adding a new book or borrowing a book.

## Quick Start
1. Ensure you have **Java 17 or later** installed.
2. Download the latest **LeBook.jar** from the releases page.
3. Place the `.jar` file in an empty folder (it will create a `data` subfolder for storage).
4. Run the application using:
   ```sh
   java -jar LeBook.jar
   ```

## Features

Note: Before continuing, you can refer to the format of book to understand what it is displaying [(Book Format)](#book-status-and-format)

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
Total books in library: 9
Now you have 1 books on the Shelf: NF-0
____________________________________________________________
```

### 2. Deleting a Book
Remove books from your library. You can delete a book using 3 methods:
1. **Delete by the books's index**
2. **Delete by the book's title and author**
3. **Delete by the book's Book ID**


**Format 1 (By Index):**

_Simply use the command `list` to see the `BOOK_NUMBER` of the book that you want to delete_
```
delete num/BOOK_NUMBER
```
**Example:**
```
delete num/1
```

**Format 2 (By Title/Author):**
```
delete bk/TITLE/AUTHOR
```
**Example:**
```
delete bk/The Great Gatsby/F. Scott Fitzgerald
```
**Format 3 (By BookID):**

[About Book ID](#book-id)
```
delete id/BOOKID
```
**Example:**
```
delete id/R-0-0
```

**Example Output:**
```
____________________________________________________________
Book deleted:
  [ ] The Great Gatsby (by F. Scott Fitzgerald) (ID: NF-0-0)
Now you have 0 books in the library
____________________________________________________________
```

### 3. Listing Books
   View all the books currently in your library, including their status, title, author, ID and dueDate.

**Format:**
```
list
```
**Example Output:**
```
____________________________________________________________
Here are the books in your library:
1. [ ] To Kill a Mockingbird (by Harper Lee) (ID: NF-0-1)
2. [ ] 1984 (by George Orwell) (ID: SCIF-0-0)
3. [X] The Hobbit (by J.R.R. Tolkien) (ID: AD-0-0) | (Due: ...)
Total books: 3
____________________________________________________________
```

### 4. Listing Overdue Books
View all the overdue books currently in your library, including their title, author, borrower name and due date.

Note: Books are **'overdue'** once they are borrowed past their due date

**Format:**
```
list overdue
```
**Example Output:**
```
____________________________________________________________
Overdue Books:
1. harry potter by rowling (Borrowed by: deanson, Due: 2025-04-01)
____________________________________________________________
```

### 5. Borrowing a Book
Mark a book as borrowed by a specific member.

_Use the command `list` to see the `BOOK_NUMBER` of the book that you want to borrow_

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

### 6. Returning a Book
Mark a borrowed book as returned.

_Use the command `list` to see the `BOOK_NUMBER` of the book that you want to return

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

### 7. Undoing Previous Command(s)
Undo the last registered command (Add, Delete, Return, Borrow)
(`undo` function defaults to undoing only the most recent single undoable command if not number is stated)

**Format:**
```
undo NUMBER_OF_COMMANDS_TO_UNDO
```
**Example:**
```
undo
undo 2
```

**Example Output:**
```
____________________________________________________________
[SUCCESS] Successfully undone: Delete a book by its index
____________________________________________________________
____________________________________________________________
[SUCCESS] Successfully undone: Add Command
____________________________________________________________
```

### 8. Finding Books
Search for specific books in your library based on different criteria.

**Format:**
```
find CRITERIA SEARCH_TERM
```
**Valid Criteria:**
- `title`: Finds books whose title contains the search term (case-insensitive).
- `author`: Finds books whose author contains the search term (case-insensitive).
- `genre`: Finds books matching the specified genre (case-insensitive). Supported genres: romance, adventure, action, horror, mystery, nonfiction, scifi.
- `id`: Finds the book with the exact unique Book ID (e.g., AD-0-1, case-insensitive).
- [About Book ID](#book-id)

**Examples:**
```
find title hobbit
find author Tolkien
find genre adventure
find id AD-0-0
```

**Example Output (Found):**
```
____________________________________________________________
Found 1 book(s) with titles containing 'hobbit':
1. [X] The Hobbit by J.R.R. Tolkien (Genre: adventure, Shelf: AD-0-0) 
       Borrowed by: Alice (Due: ...)
____________________________________________________________
```

**Example Output (Not Found):**
```
____________________________________________________________
Sorry, no books found with titles containing 'rings'.
____________________________________________________________
```

### 9. Finding Quantity of a book
Search for number of copies of a specific book in your library.

**Format:**
```
quantity / BOOK_TITLE / BOOK_AUTHOR
```

**Examples:**
```
quantity / Harry Potter / J.K. Rowling
```

**Example Output:**
```
____________________________________________________________
There are 3 copies of the book: Harry Potter (by J.K. Rowling)
____________________________________________________________
```

### 10. Listing of a specific Shelf
Allows for the viewing of what books are on a specified shelf.

- Refer to [here](#genre--genre_code) to see the available `GENRES`
- Refer to the [example](#shelves) to see how `SHELF_INDEX` works


**Format:**
```
shelf / GENRE / SHELF_INDEX
```

**Examples:**
```
shelf / romance / 1
```

**Example Output:**
```
____________________________________________________________
Here is the list of the books on shelf romance 1:
1. [ ] Romeo and Juliet (by Shakespeare)
____________________________________________________________
```

### 11. Help
Brings up the help menu for the user to refer to.

**Format:**
```
help
```

**Example Output:**
```
-------------------------------  
 Available Commands:  
-------------------------------  
1. add TITLE / AUTHOR / GENRE      - Add a new book.  
2. borrow INDEX / MEMBER_NAME      - Borrow a book (using 1-based index).  
3. delete bk / TITLE / AUTHOR      - Remove book by title and author.  
4. delete num/INDEX                - Remove book by list index (1-based).  
5. delete id/ID                    - Remove book by book ID.  
6. find CRITERIA TERM              - Search books.  
   Criteria: title, author, genre, id  
7. help                            - Show this help menu.  
8. list                            - List all book titles.  
9. list borrowed                   - List borrowed books.  
10. list overdue                   - List overdue books.  
11. list users                     - List users who have overdue books.  
12. quantity / TITLE / AUTHOR      - Shows the quantity of the specified book.  
13. return INDEX                   - Return a borrowed book (using 1-based index).  
14. shelf GENRE / SHELF_NUMBER     - List books on a specific shelf (1-based number).  
15. statistics                     - View library statistics.  
16. undo                           - Undo the last command (add/delete/borrow/return).  
17. bye                            - Exit the program.  
-------------------------------  
Supported Genres:  
  > romance, adventure, action, horror, mystery, nonfiction, scifi  
-------------------------------  
Example Usage:  
  add The Lord of the Rings / J.R.R. Tolkien / adventure  
  quantity / Harry Potter / J.K. Rowling  
  list  
  borrow 1 / Alice  
  find title lord  
  find genre adventure  
  find id AD-0-0  
  return 1  
  delete num/1  
  bye  

```

### 12. Listing Borrowed Books
View all the books in your library that are borrowed, including their title, author, borrower name and due date.

**Format:**
```
list borrowed
```
**Example Output:**
```
========================================
Borrowed Books:
1. Harry Potter (by J.K Rowling) (Borrowed by: Bob, Due: Apr 02 2025)

========================================
```

### 13. Listing Library Members with Overdue Books
View all the members who have overdue books, including the book title, book author and borrower name.

**Format:**
```
list users
```
**Example Output:**
```
========================================
Members with overdue books:
1. Bob - Harry Potter (by: J.K. Rowling)

========================================
```

### 14. Exiting the Application
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
## Book ID
A variable tied to a `Book` and is unique to every new `Book` added.

**Format** 
```
[GENRE_CODE]-[SHELF_INDEX]-[BOOK_INDEX] 
Example: R-0-0
- R refers to 'Romance'
- '0' refers to Shelf 1
- '0' refers to Book 1
```
### Genre / Genre_Code

| Genre          | Genre_Code |
|----------------|------------|
| **Romance**    | `R`        |
| **Action**     | `AC`       |
| **Adventure**  | `AD`       |
| **Horror**     | `H`        |
| **Mystery**    | `MY`       |
| **NonFiction** | `NF`       |
| **Sci-fi**     | `SCIF`     |

### Shelves

Every genre has a collection of 5 shelves indexed by a `SHELF_INDEX` of `1 to 5`

```
e.g.
1. R-0-0
- The 'R' refers to romance
- The 1st '0' refers to Shelf 1

2. AC-4-0
- The 'AC' refers to action
- The '4' refers to Shelf 5
```

## Command Summary
| Command                             | Format                            | Example                        |
|-------------------------------------|-----------------------------------|--------------------------------|
| **Add Book**                        | `add TITLE/AUTHOR/GENRE`          | `add Dune/Frank Herbert/scifi` |
| **Delete by Index**                 | `delete num/BOOK_NUMBER`          | `delete num/1`                 |
| **Delete by Book**                  | `delete bk/TITLE/AUTHOR`          | `delete bk/Dune/Frank Herbert` |
| **Delete by ID**                    | `delete id/BOOK ID`               | `delete id/R-0-0`              |
| **List Books**                      | `list`                            | `list`                         |
| **List Overdue Books**              | `list overdue`                    | `list overdue`                 |
| **List Borrowed Books**             | `list borrowed`                   | `list borrowed`                |
| **List Members with Overdue Books** | `list users`                      | `list users`                   |
| **Borrow Book**                     | `borrow BOOK_NUMBER/MEMBER`       | `borrow 2/Eve`                 |
| **Return Book**                     | `return BOOK_NUMBER`              | `return 3`                     |
| **Undo Command**                    | `undo NUMBER_OF_COMMANDS_TO_UNDO` | `undo 3`                       |
| **Find Book**                       | `find CRITERIA TERM`              | `find title dune`              |
| **Exit**                            | `bye`                             | `bye`                          |
| **Help**                            | `help`                            | `help`                         |

---


