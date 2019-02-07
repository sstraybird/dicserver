package com.zhg.dicserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="course")
public class Course {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@Column(name = "name")
	private String name;
	
    @OneToMany(
    		mappedBy = "course",
    		cascade = CascadeType.ALL,
            orphanRemoval = true
    )
	private List<Lecture> lectures = new ArrayList<>();

	@ManyToMany(mappedBy = "courses")
	private Set<Word> words = new HashSet<>();

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Word> getWords() {
		return words;
	}

	public void setWords(Set<Word> words) {
		this.words = words;
	}
}
