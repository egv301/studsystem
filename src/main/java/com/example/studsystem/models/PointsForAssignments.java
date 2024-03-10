package com.example.studsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="points_for_assignments")
public class PointsForAssignments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="assignment_id")
	private SubjectAssignments subjectAssignment;
	
	@Column(name="points")
	private Integer points;

	public PointsForAssignments() {}
	
	public PointsForAssignments(Student student, SubjectAssignments subjectAssignment, Integer points) {
		this.student = student;
		this.subjectAssignment = subjectAssignment;
		this.points = points;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public SubjectAssignments getSubject() {
		return subjectAssignment;
	}

	public void setSubject(SubjectAssignments subjectAssignment) {
		this.subjectAssignment = subjectAssignment;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "PointsForAssignments [id=" + id + ", student=" + student + ", subjectAssignment=" + subjectAssignment
				+ ", points=" + points + "]";
	}
	
	
}
