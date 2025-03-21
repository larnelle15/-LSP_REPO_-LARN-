# Using the Class-Responsibility-Collaborator (CRC) method, complete the following steps: 

## Part 1: Identify Classes

### 1. Book
I think for the Book class, we need it to represent each book in the library. Every book has its own title, author, and ISBN, plus we need to know if it's available or checked out. This is basically the main thing the whole system is tracking.

### 2. Member
We definitely need a Member class for the people using the library. This lets us keep track of who can borrow books and make sure nobody takes more than their 3 book limit. Plus we need to know who has what book at any given time.

### 3. Library
The Library class serves as the central hub that ties everything together. It manages the entire book collection and handles all the operations like borrowing, returning, and keeping track of what's available. I'm thinking of it as the main system that coordinates everything.

### 4. Librarian
Librarians need their own class because they have special permissions regular members don't have. They're the only ones who can add new books, remove outdated ones, and access all the reports about what's checked out and by whom.

## Part 2: Define Responsibilities

### Book Class
- Keeps track of all the book details (title, author, ISBN) and lets other classes access this info when needed
- Keeps track of whether it's available or checked out, and updates this status when borrowed or returned

### Member Class
- Stores the member's personal info and provides ways to access/update it
- Keeps a list of which books they currently have borrowed
- Makes sure they never exceed the 3 book borrowing limit

### Library Class
- Maintains the master collection of all books in the system
- Handles the checking out and returning processes
- Generates different views of the collection (like available books, checked out books, etc.)

### Librarian Class
- Adds newly acquired books to the library collection
- Removes books that are outdated, damaged, or lost
- Accesses and views reports about the current state of the library
- Looks up which members have which books checked out

## Part 3: Identify Collaborators

### Book Class
- **Library:** Books need to work with the Library because that's where they're stored and managed. The Library needs to know each Book's status to accurately represent what's available.
- **Member:** Books interact with Members during the borrowing/returning process. When a Member checks out a book, both classes need to update their information.

### Member Class
- **Library:** Members have to go through the Library to borrow or return books. The Library checks if the Member is eligible to borrow before letting them take a book.
- **Book:** Members keep track of which Books they've borrowed, and Books need to know who has them checked out.

### Library Class
- **Book:** The Library manages all the books, keeping track of the entire collection and each Book's availability status.
- **Member:** The Library interacts with members to process borrowing requests and make sure they don't exceed their limits.
- **Librarian:** The Library provides librarians with the tools to add/remove books and access system information.

### Librarian Class
- **Library:** Librarians use the library system to do their job. They need access to the collection and various administrative functions.
- **Book:** Librarians directly interact with books when adding new ones or removing old ones from the collection.
- **Member:** Librarians need to access member information to see who has checked out which books or if someone has overdue items.

## Citations

1. Wirfs-Brock, R., & Wilkerson, B. (1989). "Object-oriented design: a responsibility-driven approach." OOPSLA '89.
2. Beck, K., & Cunningham, W. (1989). "A laboratory for teaching object-oriented thinking." SIGPLAN Notices, 24(10).
3. Ambler, S. W. (2014). "Class Responsibility Collaborator (CRC) Models: An Agile Introduction."