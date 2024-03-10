package com.example.studsystem.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="subject_assignments")
public class SubjectAssignments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotBlank(message = "Title should not be empty")
	@Column(name="title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name="subject_id")
	private Subject subject;
	
	public SubjectAssignments() {
		
	}

	public SubjectAssignments(String title, Subject subject) {
		this.title = title;
		this.subject = subject;
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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "SubjectAssignment [id=" + id + ", title=" + title + ", subject=" + subject +"]";
	}
	
	
}
