package com.example.studsystem.dto;

import java.util.ArrayList;
import java.util.List;

public class ShowStudentSubjectDTO {
	private Long student;
	private List<SubjectDTO> studentsSubject = new ArrayList<>();
	private List<SubjectDTO> subjectsToRegister = new ArrayList<>();
	
	public ShowStudentSubjectDTO() {}
	
	public Long getStudent() {
		return student;
	}
	public void setStudent(Long student) {
		this.student = student;
	}
	public List<SubjectDTO> getStudentsSubject() {
		return studentsSubject;
	}
	public void setStudentsSubject(List<SubjectDTO> studentsSubject) {
		this.studentsSubject = studentsSubject;
	}
	public List<SubjectDTO> getSubjectsToRegister() {
		return subjectsToRegister;
	}
	public void setSubjectsToRegister(List<SubjectDTO> subjectsToRegister) {
		this.subjectsToRegister = subjectsToRegister;
	}
	
	
}
