package com.example.studsystem.dto;

import javax.validation.constraints.NotNull;

public class StudentSubjectDTO {
	
	@NotNull(message = "student must not be null")
	private Long student;
	
	@NotNull(message = "student must not be null")
	private Long subject;
	
	public StudentSubjectDTO() {}

	public Long getStudent() {
		return student;
	}

	public void setStudent(Long student) {
		this.student = student;
	}

	public Long getSubject() {
		return subject;
	}

	public void setSubject(Long subject) {
		this.subject = subject;
	}
	
	
	
	
	
}
