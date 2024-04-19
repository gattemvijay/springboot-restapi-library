package com.demo.sample.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.sample.entity.Borrower;

public interface BorrowerRepo extends JpaRepository<Borrower, Integer>{
	
	boolean existsByEmail(String email);

}
