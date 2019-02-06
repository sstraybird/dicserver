package com.zhg.dicserver.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhg.dicserver.entities.Course;
import com.zhg.dicserver.entities.Lecture;
import com.zhg.dicserver.repository.CourseRepository;
import com.zhg.dicserver.repository.LectureRepository;

@Controller
@RequestMapping(path="/lecture")
@CrossOrigin
public class LectureController {
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private CourseRepository courseRepository ;
	@PostMapping(path = "/add") 
	public @ResponseBody String addLecture(@RequestBody Lecture lecute) {	
		Optional<Course> course = courseRepository.findById(lecute.getCourse().getId());
		Course c = course.get();
		courseRepository.save(c);
		return "Saved";
	}
}
