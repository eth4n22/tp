# Choy Wayne - Project Portfolio Page

## Overview

LeBook is a CLI-based library book management system that allows users to
add, borrow, return, and track books efficiently.

## Summary of Contributions

Features Contributed:
Implemented adding to the shelf
Implemented listing of a shelf
Implemented Storage for loading and saving
Implemented the help command
Implemented the quantity command

### Code contributed:


**Code contributed:** [TP Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=WayneCh0y&tabRepo=AY2425S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


- Implemented the core logic for Shelf, Storage, QuantityManager, HelpCommand`.
- Implemented the following commands:
    - `shelf / {genre} / {shelf index}`
    - `quantity / {title} / {author}`
    - `help`

### Enhancements implemented:

- Improved the book categorisation and organisation from a global catalogue to just within a shelf.
- Enhanced the help command with detailed descriptions and examples.
- Tagged a book with a specific book ID for easier identification.
- Improved the UI when adding books, making the messages clearer and more user-friendly.
- Implemented the separation of books into sections based on their genre, for better organisation.
- Implemented shelf-specific listing to prevent information overload.

### Contributions to the UG:

- Authored usage instruction for features implemented such as `quantity`, `shelf` and `help`.

### Contributions to the DG:

- Wrote the Developer Guide sections detailing the design and implementation of:
    - **ShelvesManager** (`ShelvesManager`).
- Created/Updated the following UML diagrams for the Developer Guide:
    - **Class Diagram** (focusing on `ShelvesManager`, and their relationships).

### Contributions to team-based tasks:

- Setting up the GitHub team org/repo
- Maintain the issue tracker
- Actively reviewed PRs submitted by Team members
- Helped in the resolution of Git merge conflicts
- Engaged in discussions regarding project work flow
- Solve bugs

### Review/Mentoring Contributions:

- Provided reviews for several Pull Requests
- Reviewed PRs [PR#209, #192, etc]
- Resolved Merge Conflicts [PR#200]

- Assisted group members in debugging process and understanding implementation concepts.
---