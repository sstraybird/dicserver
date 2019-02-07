package com.zhg.dicserver.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private Long id;

	private Integer no ;
	
	private String name;

	private String url;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

    @OneToMany(
            mappedBy = "lecture",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Word> lectures = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public Integer getNo() {
        return no;
    }

    public String getName() {
        return name;
    }


}
