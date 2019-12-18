package com.baeldung.simpleapp.controllers;

import java.util.Collection;
import java.util.stream.Collectors;

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
import com.baeldung.simpleapp.util.Convertor;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;
	private final Convertor convert;

	@Autowired
	public BookController(BookService bookService, Convertor convertor) {
		this.bookService = bookService;
		this.convert = convertor;
	}

	@GetMapping
	public Collection<BookDTO> getAllBooks() {
		return bookService
				.findAll()
				.stream()
					.map(book->convert.toType(book, BookDTO.class))
					.map(object -> (BookDTO) object)
					.collect(Collectors.toList());	 
	}

	@GetMapping("{id}")
	public BookDTO getBookById(@PathVariable Long id) {
		return (BookDTO) bookService.findBookById(id)
				.map(book->convert.toType(book, BookDTO.class))
				.orElseThrow(BookNotFoundException::new);
	}

	@GetMapping("titles/{title}")
	public BookDTO getBookByTitle(@PathVariable String title) {
		return  (BookDTO) bookService.findBookByTitle(title)
				.map(book->convert.toType(book, BookDTO.class))
				.orElseThrow(BookNotFoundException::new);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book createBook(@RequestBody BookDTO newBook) {
		return bookService.createBook(convert.toType(newBook, Book.class));
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		bookService.delete(id);
	}

	@PutMapping("/{id}")
	public Book updateBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
		return bookService.updateBook(convert.toType(bookDTO, Book.class), id);
	}

	@GetMapping("/health")
	public String health() {
		return "ok";
	}
}
