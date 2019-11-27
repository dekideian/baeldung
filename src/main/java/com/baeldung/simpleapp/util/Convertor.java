package com.baeldung.simpleapp.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Convertor {
	
	private final ModelMapper modelMapper;
	
	@Autowired
	public Convertor(ModelMapper modelMapper) {
		this.modelMapper=modelMapper;
	}
	
	@SuppressWarnings("unchecked")
	public <U, V> V toType(U u, Class<?> type) {
		return (V) modelMapper.map(u, type);
	}

}
