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

import com.zhg.dicserver.entities.Category;
import com.zhg.dicserver.repository.CategoryRepository;

@Controller
@RequestMapping(path="/category")
@CrossOrigin
public class CategoryController {
	
	@Autowired
	private CategoryRepository	 categoryRepository;
	@PostMapping(path = "/add") 
	public @ResponseBody String addCategory(@RequestBody Category category) {
		categoryRepository.save(category);
		return "Saved";
	}
	
	@GetMapping(path = "/list") 
	public @ResponseBody List<Category> listCategory() {
		List<Category> categories = (List<Category>) categoryRepository.findAll();
		return categories;
	}
}
