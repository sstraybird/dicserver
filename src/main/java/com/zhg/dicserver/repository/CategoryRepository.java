package com.zhg.dicserver.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhg.dicserver.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}