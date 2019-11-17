package com.baeldung.simpleapp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
@Getter
public class ConfigurationService {

	@Value("${spring.application.name}")
	private String appName;
}
