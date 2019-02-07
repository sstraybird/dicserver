package com.zhg.dicserver.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.zhg.dicserver.entities.Course;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
}