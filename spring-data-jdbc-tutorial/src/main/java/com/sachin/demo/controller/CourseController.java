package com.sachin.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.demo.entity.Course;
import com.sachin.demo.entity.CourseChangeBO;
import com.sachin.demo.service.CourseService;

@RestController
@RequestMapping("/api/v2/course")
public class CourseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
	private CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@GetMapping
	public List<Course> getAllCourses() {
		LOGGER.info("inside CourseController.getAllCourses method");
		return courseService.getAllCourses();

	}

	@GetMapping("/{id}")
	public Course getCourseById(@PathVariable("id") int id) {
		LOGGER.info("inside CourseController.getCourseById method");
		return courseService.getCourseById(id);

	}

	@PostMapping
	public void saveCourse(@RequestBody Course c) {
		LOGGER.info("inside CourseController.saveCourse method");
		courseService.addCourse(c);
	}

	@DeleteMapping("/{id}")
	public void deleteCourse(@PathVariable("id") int id) {
		LOGGER.info("inside CourseController.deleteCourse method");
		courseService.deleteCourse(id);
	}

	@PutMapping("{id}")
	public void updateCourse(@RequestBody CourseChangeBO updatedCourse, @PathVariable("id") int id) {
		LOGGER.info("inside CourseController.updateCourse method");
		courseService.updateCourse(updatedCourse, id);
	}

}
