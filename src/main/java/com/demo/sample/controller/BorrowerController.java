package com.demo.sample.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.sample.entity.Book;
import com.demo.sample.entity.Borrower;
import com.demo.sample.service.BookService;
import com.demo.sample.service.BorrowerService;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {
	
	@Autowired
    private BookService bookService;
	
	@Autowired
    private BorrowerService borrowerService;

	
	@PostMapping("/register")
    public ResponseEntity<String> registerBorrower(@RequestBody Borrower borrower) {
        // Check if the borrower already exists
        if (borrowerService.existsByEmail(borrower.getEmail())) {
            return ResponseEntity.badRequest().body("Borrower with email " + borrower.getEmail() + " already exists.");
        }

        // Save the borrower
        borrowerService.registerBorrower(borrower);
        return ResponseEntity.ok("Borrower registered successfully.");
    }
	
    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @RequestParam Long borrowerId) {
        // Retrieve the book from the database
        Optional<Book> optionalBook = bookService.getBook((int)(long)bookId);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Book book = optionalBook.get();
        
        // Check if the book is available for borrowing
        if (book.isBorrowed()) {
            return ResponseEntity.badRequest().body("The book requested is already borrowed.");
        }
        
        // Mark the book as borrowed and associate it with the borrower
        book.setBorrowed(true);
        book.setBorrowerId(borrowerId);
        bookService.saveOrUpdateBook(book);
        
        return ResponseEntity.ok("Book borrowed successfully.");
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        // Retrieve the book from the database
        Optional<Book> optionalBook = bookService.getBook((int)(long)bookId);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Book book = optionalBook.get();
        
        // Check if the book is currently borrowed
        if (!book.isBorrowed()) {
            return ResponseEntity.badRequest().body("The book is not currently borrowed.");
        }
        
        // Mark the book as returned
        book.setBorrowed(false);
        book.setBorrowerId(null);
        bookService.saveOrUpdateBook(book);
        
        return ResponseEntity.ok("Book returned successfully.");
    }

}
