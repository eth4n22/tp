# Choy Wayne - Project Portfolio Page

## Overview

LeBook is a CLI-based library book management system that allows users to
add, borrow, return, and track books efficiently.

## Summary of Contributions
Given below are my contributions to the project.
### Features implemented:
- **Implemented listing of a shelf**
  - What it does:
    - Lists a specific shelf indicated by the librarian.
  - Justification:
    - This feature improves the product significantly as it compartmentalises the listing to just the shelf itself, making it easier for the user to view a subset of the global catalogue.
  - Highlights:
    - Implementation was particularly tricky as there was a need to deal with empty slots in between each entry of the shelf. 
- **Implementation of loading** 
  - What it does:
    - Loads the data from previous save.
  - Justification:
    - This feature significantly improves usability. It allows the librarian to keep the catalogue from the previous saves.
  - Highlights: 
    - This feature is continuously evolving based on other new features the team has implemented, as new data will need to be saved for new features. The handling of file tampering was particularly tricky.

<div style="page-break-after:always;"></div>

- **Implementation of saving**
  - What it does:
    - saves the data from current program run.
  - Justification:
    - This feature significantly improves usability. It allows the librarian to save the current catalogue when they input a new command. All changes persist automatically.
  - Highlights:
    - This feature is continuously evolving based on other new features the team has implemented.

### Code contributed:


**Code contributed:** [TP Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=WayneCh0y&tabRepo=AY2425S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


- Implemented the core logic for Shelf, Storage, QuantityManager, HelpCommand.
- Implemented the following commands:
    - `shelf / {genre} / {shelf index}`
    - `quantity / {title} / {author}`
    - `help`

### Enhancements implemented:

- Tagged a book with a specific book ID for easier identification.
- Improved the UI when adding books, making the messages clearer and more user-friendly.
- Separation of books into sections based on their genre, for better organisation.
- Shelf-specific listing to prevent information overload.
- Made objects such as `BookManager`, `Library`, etc. as Singletons, making these objects more centralised.
- Prevention and elegant failing when storage text file has been tampered with.

### Contributions to the UG:

- Authored usage instruction for features implemented such as `quantity`, `shelf` and `help`.

<div style="page-break-after:always;"></div>

### Contributions to the DG:

- Wrote the Developer Guide sections detailing the design and implementation of:
    - **Book Storage** (`Storage`).
    - **Shelving and Sections** (`ShelvesManager`).
    - **Loading of the file feature** (`loadFileContents`).
    - **Saving of book data** (`writeToFile`).
- Created/Updated the following UML diagrams for the Developer Guide:
    - **Class Diagram** (focusing on `Storage`, `ShelvesManager`, and their relationships)
    - **Sequence Diagram** (showing the flow of loading and saving of contents from the text file).

### Contributions to team-based tasks:

- Setting up the GitHub team org/repo
- Maintain the issue tracker
- Actively reviewed PRs submitted by Team members
- Helped in the resolution of Git merge conflicts
- Solve bugs

### Review/Mentoring Contributions:

- Reviewed PRs [PR#209, #192, etc]
- Resolved Merge Conflicts [PR#200]
- Assisted group members in debugging process for exception handling and understanding implementation concepts.

---