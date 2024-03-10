package com.example.studsystem.dto;

import javax.validation.constraints.NotBlank;

public class GroupDTO {
	private Long id;
	
	@NotBlank(message = "Group title cannot be blank")
	private String title;
	public GroupDTO() {}
	
	public GroupDTO(Long id, @NotBlank(message = "Group title cannot be blank") String title) {
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

	@Override
	public String toString() {
		return "Group [id=" + id + ", title=" + title + "]";
	}
}
