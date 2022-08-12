package com.sachin.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonPropertyOrder({ "courseId", "title", "credit", "courseMaterial" })
@JsonInclude(Include.NON_EMPTY) // Use NON_NULL to ignore null values. Use NON_EMPTY to ignore Empty values like
								// an empty(but not null) list.

public class Course {

	private long courseId;
	@JsonProperty(value = "courseTitle") // use this annotation to provide custom name to variable in JSON
	private String title;
	private int credit;
	@JsonManagedReference
	private CourseMaterial courseMaterial;
	@JsonManagedReference
	private Teacher teacher;
	@JsonManagedReference
	private List<Student> student;
}
