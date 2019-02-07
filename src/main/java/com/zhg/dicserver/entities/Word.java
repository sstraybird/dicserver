package com.zhg.dicserver.entities;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	private String name;

	private String definition;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "lecture_id", nullable = false)
	private Lecture lecture;

	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "word_course",
			joinColumns = @JoinColumn(name = "word_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private Set<Course> courses = new HashSet<>();


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}


	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getDefinition() {
		return definition;
	}
}
