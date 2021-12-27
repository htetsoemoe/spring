package com.jdc.form.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.form.model.dto.Course;
import com.jdc.form.model.dto.UserInput;
import com.jdc.form.model.dto.UserInput.Gender;
import com.jdc.form.model.service.CourseRepository;
import com.jdc.form.model.service.DataHolder;

@Controller
@RequestMapping("form")
public class FormController {
	
	@Autowired
	private DataHolder repo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@InitBinder
	void initWebBinder(WebDataBinder binder) {
		System.out.println(binder.getConversionService().canConvert(String.class, Course.class));
	}
		
	@GetMapping
	void index() {
	}
	
	@PostMapping
	String create(@ModelAttribute("userInput") UserInput data) {
		repo.add(data);
		return "redirect:/form";
	}
	
	@ModelAttribute("courses")
	List<Course> courses() {
		return courseRepo.getCourses();
	}
	
	@ModelAttribute("userInput")
	UserInput userInput() {
		return new UserInput();
	}
	
	@ModelAttribute("list")
	List<UserInput> allData() {
		return repo.getAllData();
	}
	
	@ModelAttribute("genders")
	Gender[] genders() {
		return Gender.values();
	}
	
	@ModelAttribute("knowledges")
	List<String> foundations() {
		return List.of("HTML", "CSS", "JavaScropt", "Database");
	}
}
