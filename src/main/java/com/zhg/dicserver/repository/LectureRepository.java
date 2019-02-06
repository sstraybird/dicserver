package com.zhg.dicserver.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhg.dicserver.entities.Lecture;

public interface LectureRepository extends CrudRepository<Lecture, Integer> {

}