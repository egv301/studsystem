package com.example.studsystem.dto;

public class PointsForAssignmentFormDTO {
	private Long student;
	private String studentFirstname;
	private String studentLastname;
	private Integer points;
	
	public PointsForAssignmentFormDTO() {}

	public PointsForAssignmentFormDTO(Long student,String studentFirstname, String studentLastname, Integer points) {
		this.student = student;
		this.studentFirstname = studentFirstname;
		this.studentLastname = studentLastname;
		this.points = points;
	}

	public void setStudent(Long id) {
		this.student = id;
	}

	public Long getStudent() {
		return student;
	}
	
	public String getStudentFirstname() {
		return studentFirstname;
	}

	public void setStudentFirstname(String studentFirstname) {
		this.studentFirstname = studentFirstname;
	}

	public String getStudentLastname() {
		return studentLastname;
	}

	public void setStudentLastname(String studentLastname) {
		this.studentLastname = studentLastname;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
}
