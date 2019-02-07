package com.zhg.dicserver.controllers;

import com.zhg.dicserver.DTO.LectureReqDTO;
import com.zhg.dicserver.DTO.LectureResDTO;
import com.zhg.dicserver.DTO.WordReqDTO;
import com.zhg.dicserver.DTO.WordResDTO;
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
import java.util.Set;

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

		Lecture lecture = lectureRepository.findById(wordReqDTO.getLectureId()).get();
		Course course = courseRepository.findById(wordReqDTO.getCourseId()).get();
		Word word = new Word();
		word.setName(wordReqDTO.getName());
		word.setDefinition(wordReqDTO.getDefinition());
		word.setLecture(lecture);
		word.setUser(userRepository.findById(wordReqDTO.getUserId()).get());
		word.getCourses().add(course);

		wordRepository.save(word) ;
		return "Saved";
	}

	@GetMapping("/all")
	public @ResponseBody List<WordResDTO> getWords(@RequestParam(name="id")String id,@RequestParam(name="parent")String parent){
        System.out.println(id);
        List<WordResDTO> wordResDTOS = new ArrayList<>();
        Iterable<Word> all = wordRepository.findAll();
        if("#".equals(id)){
            for (Word word:all) {
                WordResDTO wordResDTO = new WordResDTO();
                wordResDTO.setId(word.getId().toString()+"_#");
                wordResDTO.setParent(id);
                wordResDTO.setText(word.getName());
                wordResDTO.setChildren(true);
                wordResDTOS.add(wordResDTO);
            }
        }else if("".equals(parent)){
			System.out.println(id);
			Long findId = Long.parseLong(id.split("_")[0]);
			Word word =  wordRepository.findById(findId).get();
			String definition = word.getDefinition();
			WordResDTO d = new WordResDTO();
			d.setId(definition);
			d.setParent(id);
			d.setText("definition");
			d.setChildren(false);
			wordResDTOS.add(d);
			Set<Course> courses = word.getCourses();
			for (Course course:courses) {
				WordResDTO wordResDTO = new WordResDTO();
				wordResDTO.setId(course.getId().toString()+"_course");
				wordResDTO.setParent(id);
				wordResDTO.setText(course.getName());
				wordResDTO.setChildren(true);
				wordResDTOS.add(wordResDTO);
			}

        }else {
			System.out.println(id);
			Long findId = Long.parseLong(id.split("_")[0]);
			Optional<Course> optionalCourse = courseRepository.findById(findId);
			Course course = optionalCourse.get();
			List<Lecture> lectures = course.getLectures();
			for (Lecture lecture: lectures) {
				WordResDTO wordResDTO = new WordResDTO();
				wordResDTO.setId(lecture.getId().toString()+"_lecture");
				wordResDTO.setParent(parent+"course");
				wordResDTO.setText("lecture "+lecture.getNo() + ":" + lecture.getName());
				wordResDTO.setChildren(false);
				wordResDTOS.add(wordResDTO);
			}

		}


		return wordResDTOS;
	}
}
