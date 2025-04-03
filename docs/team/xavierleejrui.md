## Xavier Lee Jingrui - Project Portfolio Page

### Overview
LeBook is a CLI application for personal library management.

### Summary of Contributions

#### Features Implemented
- **Book Catalogue Management**: Implemented the core logic for adding, deleting, listing, and managing the status (borrowed, due dates) of books within the library catalogue (`BookManager`).
- **Advanced Search Functionality**: Developed the `find` command allowing users to search the library catalogue based on Title, Author, Genre, or unique Book ID (`BookFinder`, `SearchBy...Command` classes).

#### Code Contributed
- **Code contributed**: [TP Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=Xavierleejrui&tabRepo=AY2425S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
- **Key Implementations**:
  - `BookManager.java`: Core logic for managing the book catalogue.
  - `BookFinder.java`: Utility class encapsulating search logic.
  - `SearchByTitleCommand.java`, `SearchByAuthorCommand.java`, `SearchByGenreCommand.java`, `SearchByIDCommand.java`: Command classes implementing the different search criteria.

#### Enhancements Implemented
- **Enhanced Catalogue Management**:
  - Developed the `BookManager` class to centralize and structure book catalogue management.
  - Implemented robust methods for:
    - Adding new books with genre validation and ID assignment.
    - Deleting books by index or title/author combination.
    - Tracking detailed book status (borrowed status, borrower name, due dates).
- **Advanced Search Capability**:
  - Introduced the `find` command, allowing users to search by:
    - **Title**: Partial or full match (case-insensitive).
    - **Author**: Partial or full match (case-insensitive).
    - **Genre**: Exact match (case-insensitive).
    - **ID**: Unique book/shelf ID (case-insensitive).
- **Improved Code Design for Searching**:
  - Developed the `BookFinder` utility class to separate search logic from `BookManager`, improving code organization and maintainability.

### Contributions to the UG (User Guide)
- Authored usage instructions for core commands: `add`, `delete`, `list`, `borrow`, `return`.
- Documented the `find` command, explaining search criteria (title, author, genre, ID) with usage examples.
- Ensured command formats and examples were clear and accurate.

### Contributions to the DG (Developer Guide)
- Wrote the Developer Guide sections detailing the design and implementation of:
  - **Book Management** (`BookManager`).
  - **Searching** (`BookFinder` and related Search Commands).
- Created/Updated UML diagrams:
  - **Class Diagram** (highlighting `BookManager`, `BookFinder`, `Book`, and their relationships).
  - **Sequence Diagram** (illustrating the "Find Book by Title" workflow).

### Contributions to Team-Based Tasks
- Actively participated in code reviews for pull requests submitted by teammates.
  - Engaged in discussions regarding project structure, design choices, and best practices.
- Assisted in debugging efforts.
- Contributed to maintaining the issue tracker.

### Review/Mentoring Contributions
- Provided reviews for multiple Pull Requests (e.g., **#124, #214**), focusing on code clarity, correctness, and adherence to coding standards.
- Assisted teammates in debugging and understanding key implementation concepts related to book management and search.

