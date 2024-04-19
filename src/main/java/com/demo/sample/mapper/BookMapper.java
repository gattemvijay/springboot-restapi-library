package com.demo.sample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.demo.sample.dto.BookDTO;
import com.demo.sample.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
	
	public Book bookDTOtoBook(BookDTO bookDTO);
	public BookDTO booktoBookDTO(Book book);
	
	@Mapping(target = "id", ignore = true)
	BookDTO createBookDTOWithoutId(Book book);


}
