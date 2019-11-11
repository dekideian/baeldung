package com.baeldung.simpleapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.baeldung.simpleapp.models.Book;

public interface BookRepository extends CrudRepository<Book, Long>{
	
	List<Book> findByTitle(String title);
}
