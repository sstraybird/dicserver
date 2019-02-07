package com.zhg.dicserver.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhg.dicserver.entities.Course;

import javax.persistence.*;

public class LectureReqDTO {

	private Integer no ;
	
	private String name;

	private Long courseId;

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
}
