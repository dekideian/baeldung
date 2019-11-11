package com.baeldung.simpleapp.controllers;

import java.util.List;

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

import com.baeldung.simpleapp.exceptions.BookIdMismatchException;
import com.baeldung.simpleapp.exceptions.BookNotFoundException;
import com.baeldung.simpleapp.models.Book;
import com.baeldung.simpleapp.repositories.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping
	public Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("{id}")
	public Book getBookById(@PathVariable Long id) {
		return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
	}

	@GetMapping("titles/{title}")
	public List<Book> getBookByTitle(@PathVariable String title) {
		return bookRepository.findByTitle(title);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book createBook(@RequestBody Book newBook) {
		return bookRepository.save(newBook);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
		bookRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
		if (book.getId() != id) {
			throw new BookIdMismatchException();
		}
		bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
		return bookRepository.save(book);
	}

	@GetMapping("health")
	public String health() {
		return "ok";
	}

}
