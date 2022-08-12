package com.sachin.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student {

	private long studentId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String guardianName;
	private String guardianEmail;
	private String guardianMobile;

}
