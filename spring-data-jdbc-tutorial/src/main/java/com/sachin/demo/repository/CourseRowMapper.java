package com.sachin.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sachin.demo.entity.Course;
import com.sachin.demo.entity.CourseMaterial;
import com.sachin.demo.entity.Teacher;

public class CourseRowMapper implements RowMapper<Course> {

	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		Course course = new Course();
		CourseMaterial courseMaterial = new CourseMaterial();
		Teacher teacher = new Teacher();
		courseMaterial.setCourseMaterialId(rs.getLong("courseMaterialId"));
		courseMaterial.setUrl(rs.getString("url"));
		teacher.setTeacherFirstName(rs.getString("teacherFirstName"));
		teacher.setTeacherLastName(rs.getString("teacherLastName"));
		course.setCourseId(rs.getInt("courseId"));
		course.setTitle(rs.getString("title"));
		course.setCredit(rs.getInt("credit"));
		course.setCourseMaterial(courseMaterial);
		course.setTeacher(teacher);
		courseMaterial.setCourse(course);
		teacher.setCourse(course);
		return course;
	}

}
