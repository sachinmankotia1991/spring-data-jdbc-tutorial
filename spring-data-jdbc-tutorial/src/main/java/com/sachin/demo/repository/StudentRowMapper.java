package com.sachin.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sachin.demo.entity.Student;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setStudentId(rs.getInt("studentId"));
		student.setFirstName(rs.getString("firstName"));
		student.setLastName(rs.getString("lastName"));
		student.setEmailId(rs.getString("emailId"));
		student.setGuardianName(rs.getString("guardianName"));
		student.setGuardianEmail(rs.getString("guardianEmail"));
		student.setGuardianMobile(rs.getString("guardianMobile"));
		return student;
	}

}
