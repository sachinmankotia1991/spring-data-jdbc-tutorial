package com.sachin.demo.service;

import java.util.List;

import com.sachin.demo.entity.Course;
import com.sachin.demo.entity.CourseChangeBO;

public interface CourseService {
	List<Course> getAllCourses();

	void addCourse(Course c);

	Course getCourseById(int id);

	void deleteCourse(int id);

	void updateCourse(CourseChangeBO updatedCourse, int id);
}
