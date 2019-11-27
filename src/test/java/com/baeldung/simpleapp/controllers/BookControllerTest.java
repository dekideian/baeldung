package com.baeldung.simpleapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baeldung.simpleapp.models.Book;
import com.baeldung.simpleapp.repositories.BookRepository;

@SpringBootTest
public class BookControllerTest {

	@Autowired
	private BookController bookController;

	@Autowired
	private BookRepository bookRepository;
	private Book book1 = new Book(1, "asdf", "asdf");

	@BeforeEach
	void setMockOutput() {
		bookRepository.deleteAll();
	}

	@Test
	public void testAdd() {
		int a = 1, b = 1;
		Assertions.assertEquals(2, a + b);
	}

	@Test
	public void getAllBooks_returnsArrayOfBooks() {
		assertEquals(0, Lists.newArrayList(bookController.getAllBooks()).size());
	}

	@Test
	public void saveBook_savesBook() {
		assertEquals(0, Lists.newArrayList(bookController.getAllBooks()).size());
		bookRepository.save(book1);
		assertEquals(1, Lists.newArrayList(bookController.getAllBooks()).size());
	}

	@Test
	public void getBookById_returnsBook() {
		bookRepository.save(book1);
		assertEquals(1, Lists.newArrayList(bookRepository.findById(1L)).size());
	}

	@Test
	public void getBookByTitle_returnsBook() {
		bookRepository.save(book1);
		assertEquals(1, Lists.newArrayList(bookRepository.findByTitle("asdf")).size());
	}

	@Test
	public void delete_returnsBook() {
		bookRepository.save(book1);
		assertEquals(1, Lists.newArrayList(bookRepository.findByTitle("asdf")).size());
		bookController.delete(1L);
		assertEquals(0, Lists.newArrayList(bookRepository.findByTitle("asdf")).size());
	}
}
