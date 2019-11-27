package com.baeldung.simpleapp.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.baeldung.simpleapp.exceptions.BookIdMismatchException;
import com.baeldung.simpleapp.exceptions.BookNotFoundException;
import com.baeldung.simpleapp.models.Book;
import com.baeldung.simpleapp.repositories.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Collection<Book> findAll() {
		return (Collection<Book>) bookRepository.findAll();
	}

	public Optional<Book> findBookById(Long id) {
		return bookRepository
				.findById(id)
				.stream()
				.findAny();
	}

	public Optional<Book> findBookByTitle(@PathVariable String title) {
		return bookRepository.findByTitle(title)
				.stream()
				.findAny();
	}

	public Book createBook(Book newBook) {
		return bookRepository.save(newBook);
	}

	public void delete(Long id) {		
		Book someBook = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
		bookRepository.delete(someBook);
	}

	public Book updateBook(Book book, Long id) {
		if (book.getId() != id) {
			throw new BookIdMismatchException();
		}
		Book foundBook = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
		return bookRepository.save(foundBook);
	}
}
