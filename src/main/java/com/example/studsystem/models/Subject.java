package com.example.studsystem.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "subjects")
public class Subject {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    private Long id;
	
	@NotBlank(message="Title should not be empty.")
	@Column(name="title")
    private String title;
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private User teacher;
	
//	@OneToMany(mappedBy = "subject")
//	private List<Attendance> attendancesList;
	
	@OneToMany(mappedBy = "subject")
	private List<SubjectAssignments> assignmentsList;
	
	@ManyToMany(mappedBy = "subjects")
    private Set<Student> students = new HashSet<>();
	
	public Subject() {}
	
	public Subject(String title, User teacher) {
		this.title = title;
		this.teacher = teacher;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public User getTeacher() {
		return teacher;
	}
	
//	public List<Attendance> getAttendancesList() {
//		return attendancesList;
//	}
//
//	public void setAttendancesList(List<Attendance> attendancesList) {
//		this.attendancesList = attendancesList;
//	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	
	public List<SubjectAssignments> getAssignmentsList() {
		return assignmentsList;
	}

	public void setAssignmentsList(List<SubjectAssignments> assignmentsList) {
		this.assignmentsList = assignmentsList;
	}
	
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", title=" + title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	

	
}
