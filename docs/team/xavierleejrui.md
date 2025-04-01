# Xavier Lee Jingrui - Project Portfolio Page

## Overview

LeBook is a CLI application for personal library management.

## Summary of Contributions

### Code contributed:



**Code contributed:** [TP Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=Xavierleejrui&tabRepo=AY2425S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)



- Implemented the core logic for managing the book catalogue within `BookManager`.
- Developed the `BookFinder` utility class to encapsulate search logic.
- Implemented the four search commands:
    - `SearchByTitleCommand`
    - `SearchByAuthorCommand`
    - `SearchByGenreCommand`
    - `SearchByIDCommand` (handling searches by Shelf ID)

### Enhancements implemented:

#### Core Book Management:
- Added the underlying functionality for adding, deleting (by index and title/author), listing, borrowing, and returning books, including status tracking and due dates (`BookManager`).

#### Advanced Search Functionality:
- Implemented the `find` command allowing users to search the library catalogue based on:
    - **Book Title** (partial or full match).
    - **Book Author** (partial or full match).
    - **Book Genre** (exact match, case-insensitive).
    - **Unique Book ID** (using the shelf keyword, format e.g., AD-0-1).

### Contributions to the UG:

- Authored significant portions of the User Guide, detailing the usage and format for core commands (`add`, `delete`, `list`, `borrow`, `return`).
- Wrote the documentation for the `find` command, explaining the different search criteria (title, author, genre, shelf) and providing clear usage examples.
- Ensured command formats and constraints were clearly documented.

### Contributions to the DG:

- Wrote the Developer Guide sections detailing the design and implementation of:
    - **Book Management** (`BookManager`).
    - **Searching** (`BookFinder` and the related Search Commands).
- Created/Updated the following UML diagrams for the Developer Guide:
    - **Architecture Diagram** (highlighting components related to book management and search).
    - **Class Diagram** (focusing on `BookManager`, `BookFinder`, `Book`, and their relationships).
    - **Sequence Diagram** (illustrating the "Find Book by Title" workflow).

### Contributions to team-based tasks:

- Actively participated in code reviews for pull requests submitted by teammates.
- Engaged in discussions regarding project structure and implementation best practices.

### Review/Mentoring Contributions:

- Provided reviews for several Pull Requests, focusing on code clarity, correctness, and adherence to coding standards. Examples:
    - PR #124

- Assisted teammates in debugging and understanding key implementation concepts.

---
