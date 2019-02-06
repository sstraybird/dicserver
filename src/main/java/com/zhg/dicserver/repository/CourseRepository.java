package com.zhg.dicserver.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhg.dicserver.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {

}