package com.zhg.dicserver.controllers;

import com.zhg.dicserver.DTO.LectureResDTO;
import com.zhg.dicserver.DTO.UserReqDTO;
import com.zhg.dicserver.DTO.WordReqDTO;
import com.zhg.dicserver.entities.Course;
import com.zhg.dicserver.entities.Lecture;
import com.zhg.dicserver.entities.User;
import com.zhg.dicserver.entities.Word;
import com.zhg.dicserver.repository.CourseRepository;
import com.zhg.dicserver.repository.LectureRepository;
import com.zhg.dicserver.repository.UserRepository;
import com.zhg.dicserver.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/user")
@CrossOrigin
public class UserController {
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private CourseRepository courseRepository ;

	@Autowired
	private WordRepository wordRepository;

	@Autowired
	private UserRepository userRepository ;
	@PostMapping(path = "/add") 
	public @ResponseBody String addLecture(@RequestBody UserReqDTO userReqDTO) {
		User user = new User();
		user.setName(userReqDTO.getName());
		user.setPassword(userReqDTO.getPassword());
		user.setEmail(userReqDTO.getEmail());
		userRepository.save(user) ;
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
