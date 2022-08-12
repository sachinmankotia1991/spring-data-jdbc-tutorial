package com.sachin.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sachin.demo.entity.Course;
import com.sachin.demo.entity.CourseChangeBO;
import com.sachin.demo.error.NoDataFoundException;
import com.sachin.demo.repository.CourseDAOImpl;

@Service
public class CoursesServiceImpl implements CourseService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CoursesServiceImpl.class);

	private CourseDAOImpl courseDAOImpl;

	public CoursesServiceImpl(CourseDAOImpl courseDAOImpl) {
		this.courseDAOImpl = courseDAOImpl;
	}

	@Override
	public List<Course> getAllCourses() {
		LOGGER.info("inside CoursesServiceImpl.getAllCourses method");
		return courseDAOImpl.list();
	}

	@Override
	public void addCourse(Course c) {
		LOGGER.info("inside CoursesServiceImpl.addCourse method");
		courseDAOImpl.create(c);
	}

	@Override
	public Course getCourseById(int id) {
		LOGGER.info("inside CoursesServiceImpl.getCourseById method");
		Optional<Course> optCourse = courseDAOImpl.get(id);
		if (!optCourse.isEmpty()) {
			return optCourse.get();
		} else {
			throw new NoDataFoundException("No such data present");

		}
	}

	@Override
	public void deleteCourse(int id) {
		LOGGER.info("inside CoursesServiceImpl.deleteCourse method");
		courseDAOImpl.delete(id);
	}

	@Override
	public void updateCourse(CourseChangeBO updatedCourse, int id) {
		LOGGER.info("inside CoursesServiceImpl.updateCourse method");
		courseDAOImpl.update(updatedCourse, id);
	}

}
