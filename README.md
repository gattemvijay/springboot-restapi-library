**MyLibrary API Documentation**
**Introduction**
Welcome to the documentation for the MyLibrary API. This API provides endpoints to manage books and borrowers in a library system.

Base URL
The base URL for accessing the API is:

arduino
Copy code
http://localhost:9095
Authentication
The MyLibrary API does not require authentication at this time.

Endpoints
Books
Get All Books
URL: /books/getAllBooks
Method: GET
Description: Retrieves a list of all books in the library.
Response: A JSON array of book objects.
Add Book
URL: /books/saveOrUpdateBook
Method: POST
Description: Adds a new book to the library or updates an existing one.
Request Body: A JSON object representing the book to be added or updated.
json
Copy code
{
  "isbn": "1234567890123",
  "title": "Book Title",
  "author": "Author Name"
}
Response: A success message indicating that the book was added or updated successfully.
Get Book by ID
URL: /books/getBook/{id}
Method: GET
Description: Retrieves a book by its ID.
URL Parameters: id - The ID of the book to retrieve.
Response: A JSON object representing the book.
Borrowers
Register Borrower
URL: /borrower/register
Method: POST
Description: Registers a new borrower in the library system.
Request Body: A JSON object representing the borrower to be registered.
json
Copy code
{
  "name": "Borrower Name",
  "email": "borrower@example.com"
}
Response: A success message indicating that the borrower was registered successfully.
Borrowing
Borrow Book
URL: /borrower/borrow
Method: POST
Description: Allows a borrower to borrow a book from the library.
Request Body: A JSON object representing the borrowing request.
json
Copy code
{
  "borrowerId": 1,
  "bookId": 123
}
Response: A success message indicating that the book was borrowed successfully.
Return Book
URL: /borrower/return
Method: POST
Description: Allows a borrower to return a borrowed book to the library.
Request Body: A JSON object representing the return request.
json
Copy code
{
  "borrowerId": 1,
  "bookId": 123
}
Response: A success message indicating that the book was returned successfully.
Error Handling
If an error occurs while processing a request, the API will return an appropriate HTTP status code along with a JSON response containing an error message.


**Database Schema:**

**Database Management System (DBMS):** MySQL is the DBMS used and any specific configuration settings required for it can be obtained from the application.properties file. As there are only two simple tables, MySQL DB is enough to handle the data volume for this application.
Tables: List of all the tables in the database schema along with their purpose.
1. tbl_Book(id as primary key,isbn,author, title)
2. tbl_Borrower(id asprimary key, name,email (unique))

**Initialization Scripts:**
These are the script for the DB tables creation

**CREATE DATABASE mylibrary;**

**USE mylibrary;**

CREATE TABLE tbl_Book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
);

CREATE TABLE tbl_Borrower (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);


**Data Access Layer:**
ORM Framework: The Spring Data JPA gets the connections details from the application.properties file to interact with the database.

**Repository Interfaces:** The Jpa classes, which extend JPARepository are used to perform CRUD (Create, Read, Update, Delete) operations on the database tables.

**Database Configuration:**
Database configuration information can be seen in application.properties file including the connection settings such as the database URL, username, password, and any other relevant configuration properties.


