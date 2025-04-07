# Ng Ding Hui, Jermaine - Project Portfolio Page

## Overview

LeBook is a CLI-based library book management system that allows users to
add, borrow, return, and track books efficiently.

## Summary of Contributions

### Features Implemented:

- **Parse user input appropriately**
  - What it does: 
    - Processes user input, extracts relevant information, and calls the corresponding command. This allows users to conveniently enter a single string.
  - Justification:
    - This feature significantly improves usability. It ensures that user inputs are interpreted correctly, reducing errors and making the system more responsive.
  - Highlights:
    - The implementation required designing a structured way to process various types of input. It was also necessary to handle edge cases such as invalid input formats and missing parameters.
- **Listing of borrowed books**
  - What it does:
    - Allows users to view a list of all books currently borrowed by members, making it easier to track borrowed items.
  - Justification:
    - This feature improves book management by providing a quick and structured overview of borrowed books. It allows librarians to monitor the book statuses.
  - Highlights:
    - The implementation required iterating through the list of members and their borrowed books. Efficient data retrieval and formatting were key considerations to ensure that the listing remains clear and concise.
- **Listing of users with overdue books**
  - What it does:
    - Lists members who have overdue books along with the details of the overdue items. This feature is implemented with the Member and MemberManager classes.
  - Justification:
    - This feature enhances the book management system by making it easier to track overdue books and follow up with users who need to return them.
  - Highlights:
    - The implementation required the creation of a Member class, as well as a MemberManager class to handle all the library members. This feature involved checking each member’s borrowed books for overdue status and formatting the output for readability.

### Code Contributed:

**Code contributed:** [TP Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=jenmarieng&tabRepo=AY2425S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented:

- Ensure user input parsing is more robust
- Added Member and MemberManager classes for handling of library members to increase functionality
- Enhanced borrowing of books by allowing librarians to add who borrowed the book
- Added unit tests for major commands

### Contributions to the UG:

- Authored usage instruction for features implemented such as parser, `list borrowed` and `list users`.

### Contributions to the DG:

- Wrote the Developer Guide sections detailing the design and implementation of:
    - **Parser**
    - **List Users with Overdue Books** (`list users`)
- Created/Updated the following UML diagrams for the Developer Guide:
    - **Class Diagram** (focusing on `Parser`, and its relationships)
    - **Sequence Diagram** (focusing on List Users with Overdue Books Feature)
- Wrote the Appendix for the Developer Guide

### Contributions to Team-based Tasks:

- Actively reviewed PRs submitted by team members
- Aided in the resolution of Git merge conflicts
- Engaged in discussions regarding project work flow
- Updating sections of Developer Guide that is not specific to any feature
- Maintaining the issue tracker
- Debugging
- Extensive testing of project to find possible bugs

### Review/Mentoring Contributions:

- Provided reviews for several Pull Requests [PR#216, #193, etc]
- Assisted group members in debugging process and understanding implementation concepts

### Contributions beyond the Project Team:

- Reported 6 bugs for another team's product during the PE dry run: https://github.com/jenmarieng/ped

---
