package com.zhg.dicserver.repository;

import com.zhg.dicserver.entities.Lecture;
import com.zhg.dicserver.entities.Word;
import org.springframework.data.repository.CrudRepository;

public interface WordRepository extends CrudRepository<Word, Long> {

}