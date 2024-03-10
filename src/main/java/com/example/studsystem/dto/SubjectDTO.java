package com.example.studsystem.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SubjectDTO {
	
	private Long id;
	
	@NotBlank(message="Title should not be empty.")
	@Size(min = 2, message = "Subject title must have at least 2 symbols")
	private String title;
	
	@NotNull(message = "Teacher id cannot be null.")
	private Long teacher;

	public SubjectDTO(){}

	public SubjectDTO(Long id,String title, Long teacher){
		this.id = id;
		this.title = title;
		this.teacher = teacher;
	}
	
	public SubjectDTO(Long id, String title){
		this.id = id;
		this.title = title;
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
	
	public Long getTeacher() {
		return teacher;
	}

	public void setTeacher(Long teacher) {
		this.teacher = teacher;
	}


	@Override
	public String toString() {
		return "Subject [id=" + id + ", title=" + title + "]";
	}
	
}
