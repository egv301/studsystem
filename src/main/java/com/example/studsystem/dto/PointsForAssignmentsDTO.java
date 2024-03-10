package com.example.studsystem.dto;

import javax.validation.constraints.NotNull;

public class PointsForAssignmentsDTO {
	
	@NotNull(message = "Student id cannot be blank.")
	private Long student;
	
	@NotNull(message = "Assignment id cannot be blank.")
	private Long subjectAssignment;
	
	@NotNull(message = "Points cannot be blank.")
	private Integer points;

	public PointsForAssignmentsDTO() {}

	public Long getStudent() {
		return student;
	}

	public void setStudent(Long student) {
		this.student = student;
	}

	public Long getSubjectAssignment() {
		return subjectAssignment;
	}

	public void setSubjectAssignment(Long subjectAssignment) {
		this.subjectAssignment = subjectAssignment;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "PointsForAssignmentsDTO [student=" + student + ", subjectAssignment=" + subjectAssignment + ", points="
				+ points + "]";
	}

	

}
