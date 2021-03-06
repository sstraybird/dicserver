package com.zhg.dicserver.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zhg.dicserver.DTO.LectureReqDTO;
import com.zhg.dicserver.DTO.LectureResDTO;
import com.zhg.dicserver.entities.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhg.dicserver.entities.Course;
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
	public @ResponseBody String addLecture(@RequestBody LectureReqDTO lectureDTO) {
		Optional<Course> courseOptional = courseRepository.findById(lectureDTO.getCourseId());
		Course course = courseOptional.get();
		course.setId(lectureDTO.getCourseId());
		Lecture lecture = new Lecture();
		lecture.setName(lectureDTO.getName());
		lecture.setNo(lectureDTO.getNo());

		lecture.setCourse(course);
		lectureRepository.save(lecture) ;
		return "Saved";
	}

	@GetMapping("/list")
	public @ResponseBody List<LectureResDTO> getLecturesByCourseId(@RequestParam(name="courseId")Long courseId){
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		Course course = courseOptional.get();
		List<Lecture> lectures = course.getLectures();
		List<LectureResDTO> lectureResDTOS = new ArrayList<>();
		for (Lecture lecture:lectures) {
			LectureResDTO lectureResDTO = new LectureResDTO();
			lectureResDTO.setId(lecture.getId());
			lectureResDTO.setNo(lecture.getNo());
			lectureResDTO.setName(lecture.getName());
			lectureResDTOS.add(lectureResDTO);
		}
		return lectureResDTOS;
	}
}
