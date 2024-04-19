package com.demo.sample.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.sample.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{	
	

}
