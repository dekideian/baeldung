package com.baeldung.simpleapp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

	private final long id;
	private final String title;
	private final String author;

	public BookDTO(long id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}
}
