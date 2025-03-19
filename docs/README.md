# LeBook

LeBook is a lightweight command-line application for managing your personal book collection. Keep track of books you own, borrow, and lend out with simple text commands.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Development](#development)
- [Testing](#testing)
- [License](#license)

## Features

- **Book Management**: Add and delete books from your collection
- **Status Tracking**: Mark books as borrowed or returned
- **Book Listing**: View all books in your library with status indicators
- **Persistence**: Your library is automatically saved between sessions
- **Simple Interface**: Easy-to-remember commands with clear feedback

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Gradle 7.0+ (for building from source)

### Installation

#### Option 1: Download JAR file
1. Download the latest `tp.jar` from the [releases page](https://github.com/yourusername/lebook/releases)
2. Place the JAR file in your preferred directory

#### Option 2: Build from source
1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/lebook.git
   ```
2. Navigate to the project directory
   ```bash
   cd tp
   ```

### Running LeBook
```bash
java -jar tp.jar
```

On first run, LeBook will create a data directory for storing your book collection.

## Usage

LeBook supports the following commands:

| Command | Format | Description |
|---------|--------|-------------|
| Add Book | `add TITLE / AUTHOR` | Adds a new book to your library |
| Delete Book | `delete BOOK_NUMBER` | Removes a book from your library |
| List Books | `list` | Displays all books in your library |
| Borrow Book | `borrow BOOK_NUMBER` | Marks a book as borrowed |
| Return Book | `return BOOK_NUMBER` | Marks a book as returned |
| Exit | `exit` | Exits the application |

For a more detailed guide, please refer to the [User Guide](docs/UserGuide.md).

## Project Structure

```
seedu/duke/
├── LeBook.java                 # Main application class
├── book/                       # Book-related classes
│   ├── Book.java               # Book entity
│   └── BookManager.java        # Book collection management
├── commands/                   # Command classes
│   ├── AddCommand.java
│   ├── Command.java            # Base command interface
│   ├── Commands.java           # Command types enum
│   ├── DeleteCommand.java
│   ├── ExitCommand.java
│   ├── ListCommand.java
│   └── UpdateStatusCommand.java
├── exception/
│   └── LeBookException.java    # Custom exceptions
├── parser/
│   └── Parser.java             # Command parser
├── storage/
│   └── Storage.java            # File operations
└── ui/
    └── Ui.java                 # User interface
```

## Development

### File Format

The storage file (`data/LeBook_data.txt`) uses a pipe-separated format:
```
Title | Author | BorrowedStatus | ReturnDueDate(optional)
```

Example:
```
The Great Gatsby | F. Scott Fitzgerald | 0
1984 | George Orwell | 1 | 2025-04-15
```

## Testing

LeBook uses JUnit 5 for testing. To run the tests:

```bash
./gradlew test
```

The test suite includes unit tests for:
- `BookManager` operations
- Command execution
- File parsing and storage
