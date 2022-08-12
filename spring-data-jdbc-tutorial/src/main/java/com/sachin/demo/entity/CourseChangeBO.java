package com.sachin.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseChangeBO {

	private long courseId;
	private String title;
	private int credit;
	private int courseMaterialId;
}
