# Ethan Tang Wen Yi - Project Portfolio Page

## Overview

LeBook is a CLI-based library book management system that allows users to
add, borrow, return, and track books efficiently.

## Summary of Contributions

Features Contributed:
Date tracking of books
Overdue Books implementation
Listing of Overdue books
Ability to undo previous commands
Statistics feature

### Code contributed:


**Code contributed:** [TP Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=eth4n22&tabRepo=AY2425S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


- Implemented the core logic for Book, Ui, UndoManager, StatisticsCommand`.
- Implemented the following commands:
    - `list overdue`
    - `undo`
    - `undo {number}`
    - `statistics`

### Enhancements implemented:

- Implemented UndoCommand with a multi-level undo functionality
- Implemented return due dates to be set to 2 weeks after date of borrowing
- Added unit tests for major commands

#### Core Book Management:
- Added the underlying functionality for adding, deleting (by index and title/author), listing, borrowing, and returning books, including status tracking and due dates (`BookManager`).

#### Advanced Search Functionality:
- Implemented the `find` command allowing users to search the library catalogue based on:
    - **Book Title** (partial or full match).
    - **Book Author** (partial or full match).
    - **Book Genre** (exact match, case-insensitive).
    - **Unique Book ID** (using the shelf keyword, format e.g., AD-0-1).

### Contributions to the UG:

- Authored usage instruction for features implemented such as `list overdue`, `statistics` and `undo`.

### Contributions to the DG:

- Wrote the Developer Guide sections detailing the design and implementation of:
    - **Undo Manager** (`UndoManager`).
- Created/Updated the following UML diagrams for the Developer Guide:
    - **Class Diagram** (focusing on `UndoManager`, and their relationships).

### Contributions to team-based tasks:

- Actively reviewed PRs submitted by Team members
- Helped in the resolution of Git merge conflicts
- Engaged in discussions regarding project work flow

### Review/Mentoring Contributions:

- Provided reviews for several Pull Requests
- Reviewed PRs [PR#207, #206, etc]

- Assisted group members in debugging process and understanding implementation concepts.
---
