package com.example.studsystem.dto;

import java.util.List;

public class StudentForUpdateDTO {
	
	private Long id;
	private StudentDTO student;
    private List<GroupDTO> groupsList;
    
    public StudentForUpdateDTO() {}
    
    public StudentForUpdateDTO(StudentDTO student, List<GroupDTO> groupsList) {
		this.student = student;
		this.groupsList = groupsList;
	}
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	public List<GroupDTO> getGroupsList() {
		return groupsList;
	}
	public void setGroupsList(List<GroupDTO> groupsList) {
		this.groupsList = groupsList;
	}
    
    

	
}
