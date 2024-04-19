package com.demo.sample.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

	private String id;
	
    @NotBlank(message = "ISBN is required")
	private String isbn;
    
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be at most 255 characters long")
	private String title;
    
    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author must be at most 255 characters long")
	private String author;

}
