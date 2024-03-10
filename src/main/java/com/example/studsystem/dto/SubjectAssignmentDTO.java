package com.example.studsystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class SubjectAssignmentDTO {
	private Long id;
	
	@NotBlank(message = "Title should not be empty")
	private String title;
	
	@NotNull(message = "Subject cannot be null")
	private Long subject;
	
	public SubjectAssignmentDTO() {}
	
	public SubjectAssignmentDTO(Long id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public SubjectAssignmentDTO(Long id,String title,Long subject) {
		this.id = id;
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

	public Long getSubject() {
		return subject;
	}

	public void setSubject_id(Long subject) {
		this.subject = subject;
	}
}
