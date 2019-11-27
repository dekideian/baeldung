package com.baeldung.simpleapp.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO{

	private long id;
	private String title;
	private String author;

	public BookDTO(long id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}
}
