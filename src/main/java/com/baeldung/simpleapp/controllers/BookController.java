package com.baeldung.simpleapp.controllers;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.simpleapp.dtos.BookDTO;
import com.baeldung.simpleapp.exceptions.BookNotFoundException;
import com.baeldung.simpleapp.models.Book;
import com.baeldung.simpleapp.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;
	private final ModelMapper modelMapper;

	@Autowired
	public BookController(BookService bookService, ModelMapper modelMapper) {
		this.bookService = bookService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public Collection<BookDTO> getAllBooks() {
		return bookService
				.findAll()
				.stream()
					.map(this::convertToDTO)
					.collect(Collectors.toList());	 
	}

	@GetMapping("{id}")
	public BookDTO getBookById(@PathVariable Long id) {
		return bookService.findBookById(id)
				.map(this::convertToDTO)
				.orElseThrow(BookNotFoundException::new);
	}

	@GetMapping("titles/{title}")
	public BookDTO getBookByTitle(@PathVariable String title) {
		return bookService.findBookByTitle(title)
				.map(this::convertToDTO)
				.orElseThrow();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book createBook(@RequestBody BookDTO newBook) {
		return bookService.createBook(convertToEntity(newBook));
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		bookService.delete(id);
	}

	@PutMapping("/{id}")
	public Book updateBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
		return bookService.updateBook(convertToEntity(bookDTO), id);
	}

	@GetMapping("/health")
	public String health() {
		return "ok";
	}
	
	//TODO create a model interface + dto interface, than a single generic conversion class
	private BookDTO convertToDTO(Book book) {
		return modelMapper.map(book, BookDTO.class);
	}
	
	private Book convertToEntity(BookDTO bookDto){
		return modelMapper.map(bookDto, Book.class);
	}

}
