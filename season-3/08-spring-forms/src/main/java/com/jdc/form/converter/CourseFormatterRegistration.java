package com.jdc.form.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;

@Configuration
public class CourseFormatterRegistration {
	
	@Autowired
	private CourseFormatter formatter;

	@Autowired
	void addConverter(FormatterRegistry registory) {
		System.out.println("Register Converter");
		registory.addFormatter(formatter);
	}
	

}
