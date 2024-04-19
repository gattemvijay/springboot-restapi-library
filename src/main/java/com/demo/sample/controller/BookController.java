package com.demo.sample.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.sample.entity.Book;
import com.demo.sample.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {

		return bookService.getAllBooks();

	}

	// Create or update a book
	@PostMapping("/saveOrUpdateBook")
	public ResponseEntity<String> saveOrUpdateBook(@RequestBody Book book) {
	    // Check if the provided book has an ID
	    if (book.getId() == null) {
	        // If the book doesn't have an ID, it's a new book, no need to check ISBN
	    } else {
	        // If the book has an ID, it's an update operation
	        // Check if the book with the given ID exists
	        Optional<Book> existingBook = bookService.getBook((int)(long)book.getId());
	        if (existingBook.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // Save or update the book
	    bookService.saveOrUpdateBook(book);

	    return ResponseEntity.ok("Book saved or updated successfully.");
	}


	@GetMapping("/getBook/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Integer id) {

		Optional<Book> optionalBook = bookService.getBook(id);

		if (optionalBook.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			
			System.out.println("Book with id "+id +" returned successfully.");
			return ResponseEntity.ok(optionalBook.get());
		}
	}

}
