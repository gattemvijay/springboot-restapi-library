package com.demo.sample.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.sample.dto.BookDTO;
import com.demo.sample.entity.Book;
import com.demo.sample.repo.BookRepo;

@Service
public class BookService {
	

	@Autowired
	BookRepo bookRepo;
    
	public List<Book> getAllBooks(){
		
		return  bookRepo.findAll();
		
	}
	
	public void addBook(Book book) {
		
		bookRepo.save(book);
		
	}
	
	public Optional<Book> getBook(Integer id) {
		
		return bookRepo.findById(id);
		
	}

	 // Save or update a book
    public void saveOrUpdateBook(Book book) {
    	bookRepo.save(book);
    }
    

}
