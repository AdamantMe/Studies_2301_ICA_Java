# ChatViewer

ChatViewer is a standalone graphical application that allows users to view chat conversations stored in a file with a specific format. It provides a simple and intuitive interface for displaying chat messages.

## Features

- Choose and open a chat file
- Display chat messages in a scrollable interface
- Supports timestamp, name, and message content for each chat message
- Automatically detects and handles common chat file formats

## Installation

To use ChatViewer, follow these steps:

1. Clone the repository or download the source code.
2. Open the project in an IDE (e.g., IntelliJ IDEA).
3. Build and run the application.

## Usage

1. Launch the ChatViewer application.
2. Click the "Choose File" button to select a chat file.
3. The chat messages will be displayed in the main window.
4. Use the scrollbar to scroll through the chat history.

## File Format

The chat file should follow the specified format:

- Each chat message should be represented by separate lines.
- Each line should start with a specific keyword followed by a colon ":" and the corresponding value.
- The keywords include "Time", "Name", and "Message".

Example chat file:
Time:12:34:56
Name:Adam
Message:Hello Bob :)
