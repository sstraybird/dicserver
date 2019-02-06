package com.zhg.dicserver.controllers;

import com.zhg.dicserver.DTO.LectureReqDTO;
import com.zhg.dicserver.DTO.LectureResDTO;
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
@RequestMapping(path="/word")
@CrossOrigin
public class WordController {
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private CourseRepository courseRepository ;

	@Autowired
	private WordRepository wordRepository;

	@Autowired
	private UserRepository userRepository ;
	@PostMapping(path = "/add") 
	public @ResponseBody String addLecture(@RequestBody WordReqDTO wordReqDTO) {
		Optional<Lecture> optionalLecture = lectureRepository.findById(wordReqDTO.getLectureId());
		Lecture lecture = optionalLecture.get();
		Word word = new Word();
		word.setName(wordReqDTO.getName());
		word.setLecture(lecture);
		Optional<User> optionalUser = userRepository.findById(21);

		word.setUser(optionalUser.get());
		wordRepository.save(word) ;
		return "Saved";
	}

	@GetMapping("/list")
	public @ResponseBody List<LectureResDTO> getLecturesByCourseId(@RequestParam(name="courseId")Integer courseId){
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
