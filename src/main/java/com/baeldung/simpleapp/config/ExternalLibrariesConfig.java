package com.baeldung.simpleapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalLibrariesConfig {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
