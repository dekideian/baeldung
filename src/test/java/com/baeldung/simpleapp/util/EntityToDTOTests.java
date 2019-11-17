package com.baeldung.simpleapp.util;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.baeldung.simpleapp.dtos.BookDTO;
import com.baeldung.simpleapp.models.Book;

@SpringBootTest
public class EntityToDTOTests {
	
	private ModelMapper modelMapper = new ModelMapper();
	 
    @Test
    public void whenConvertBookEntityToBookDto_thenCorrect() {
        Book book = new Book();
        book.setId(Long.valueOf(1));
        book.setTitle("luceafarul");
        book.setAuthor("eminescu");
 
        BookDTO bookDto = modelMapper.map(book, BookDTO.class);
        assertEquals(book.getId(), bookDto.getId());
        assertEquals(book.getTitle(), bookDto.getTitle());
        assertEquals(book.getAuthor(), bookDto.getAuthor());
    }
 
    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
    	BookDTO bookDto = new BookDTO(1L, "a", "b");
        Book post = modelMapper.map(bookDto, Book.class);
        assertEquals(bookDto.getId(), post.getId());
        assertEquals(bookDto.getTitle(), post.getTitle());
        assertEquals(bookDto.getAuthor(), post.getAuthor());
    }
}