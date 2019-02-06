package com.zhg.dicserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhg.dicserver.entities.Course;
import com.zhg.dicserver.repository.CourseRepository;

@Controller
@RequestMapping(path="/course")
@CrossOrigin
public class CourseController {
	@Autowired
	private CourseRepository courseRepository;
	@PostMapping(path = "/add") 
	public @ResponseBody String addCourse(@RequestBody Course course) {
		courseRepository.save(course);
		return "Saved";
	}
	
	@GetMapping(path = "/list") 
	public @ResponseBody List<Course> listCourse() {
		List<Course> courses = (List<Course>) courseRepository.findAll();
		return courses;
	}
}
