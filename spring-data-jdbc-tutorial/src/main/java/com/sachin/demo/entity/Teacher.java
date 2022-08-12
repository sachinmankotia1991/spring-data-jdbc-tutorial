package com.sachin.demo.entity;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
	@Id
	private long teacherId;
	private String teacherFirstName;
	private String teacherLastName;
	@JsonBackReference
	private Course course;

}
