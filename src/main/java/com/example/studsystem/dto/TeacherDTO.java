package com.example.studsystem.dto;

import javax.validation.constraints.Size;

public class TeacherDTO{
    
    private Long id;
    
    @Size(min=2, message = "At least 2 symbols")
    private String firstname;
    
    @Size(min=2, message = "At least 2 symbols")
    private String lastname;
    
    public TeacherDTO() {
    }
	
	public TeacherDTO(Long id,String firstname,String lastname) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
}