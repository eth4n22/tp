# Ethan Tang Wen Yi - Project Portfolio Page

## Overview

LeBook is a CLI-based library book management system that allows users to
add, borrow, return, and track books efficiently.

## Summary of Contributions

### Features Implemented:

- **Implemented Date tracking of books**
  - What it does:
    - When a book is borrowed, the system automatically assigns a return due date that is 2 weeks from the borrowing date.
  - Justification:
    - Enables tracking of overdue books, improving the accountability for borrowed items.
  - Highlights:
    - Due date is stored and shown in book listing.
    - Used as foundation for other features like `list overdue` ,`list overdue users` and `statistics`.

- **Implemented listing of overdue books**
  - What it does:
    - List all books who have yet to have been returned despite its due date being passed.
  - Justification:
    - Allows librarians to quickly identify overdue books and follow up with the respective members.
  - Highlights:
    - Integrates with due date tracking.
    - Displays borrower name and the respective due dates of overdue books.

- **Implemented Undo functionality with multi-level support**
  - What it does:
    - Allows librarians to undo the effects of previously made commands (e.g., `add`, `delete`, `borrow` and `return`).
    - Also provides the option to specify how many commands to undo.
  - Justification:
    - Allows users to have the flexibility to revert accidental errors.
  - Highlights:
    - `UndoManager` maintains a stack of commands that are undoable.
    - Accepts commands such as `undo` or `undo 5` for multi-level undo.
    - Validates undo count and displays the appropriate error/success messages.

- **Implemented user confirmation for undo requests**
  - What it does:
    - Asks users to confirm their undo request with `y/n` after entering an `undo` command.
  - Justification:
    - Prevents accidental undos such as inputting `20` instead of `2`.
    - Gives users a chance to reconsider undo action.
  - Highlights:
    - Accepts only `y`, `Y`, `n` or `N` as valid responses.
    - Displays warning for invalid inputs.

- **Implemented statistics feature**
  - What it does:
    - Displays key library statistics such as total number of book copies, total number of borrowed books, total number of overdue books,
      total number of unique titles and list of unique titles.
  - Justification:
    - Gives users a quick overview of the library's status in order to aid inventory assessments.
  - Highlights:
    - Statistics is dynamically computed based on current state of catalogue.
    - Neatly formatted output for readability.

### Code contributed:

**Code contributed:** [TP Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=eth4n22&tabRepo=AY2425S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

- Implemented the core logic for `Book`, `Ui`, `UndoManager`, `StatisticsCommand`.
- Implemented the following commands:
    - `list overdue`
    - `undo`
    - `undo {number}`
    - `statistics`

### Enhancements implemented:

- Developed multi-level undo support using a stack-based approach in `UndoManager`.
- Validated undo command inputs and added confirmation logic for undoing multiple commands.
- Extended borrowing feature to auto-generate a return due date.
- Wrote JUnit tests for multiple classes.

### Contributions to the UG:

- Documented usage and examples for `list overdue, `statistics`, `undo` and `undo {number}`
- Ensured clarity in command formats.

### Contributions to the DG:

- Authored DG sections for UndoCommand and UndoManager.
- Created UML Diagrams for Undo feature (Class and Sequence diagram) and Statistics (Sequence Diagram).
- Ensured consistency and standardization of command formats across diagrams and documentation.

### Contributions to team-based tasks:

- Actively reviewed PRs submitted by Team members
- Helped in the resolution of Git merge conflicts
- Engaged in discussions regarding project work flow

### Review/Mentoring Contributions:

- Provided reviews for several Pull Requests
- Reviewed PRs [PR#207, #206, etc]

- Assisted group members in debugging process and understanding implementation concepts.
---
