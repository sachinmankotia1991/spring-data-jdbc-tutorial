package com.sachin.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@JsonPropertyOrder({ "courseMaterialId", "url", "course" })

public class CourseMaterial {
	private Long courseMaterialId;
	private String url;
	@JsonBackReference
	private Course course;
}
