package com.demo.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.sample.entity.Borrower;
import com.demo.sample.repo.BorrowerRepo;

@Service
public class BorrowerService {
	
	@Autowired
	BorrowerRepo borrowerRepo;
	
	public boolean existsByEmail(String email) {
		
		return borrowerRepo.existsByEmail(email);
		 
	}

	public void registerBorrower(Borrower borrower) {

		borrowerRepo.save(borrower);
		
	}


}
