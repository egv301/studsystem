package com.example.studsystem.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentDTO{
    
    private Long id;
    
    @Size(min=2, message = "At least 2 symbols")
    private String firstname;
    
    @Size(min=2, message = "At least 2 symbols")
    private String lastname;
    
   
    @NotNull(message = "Group cannot be null")
    private Long group;
    
    public StudentDTO() {
    }
    
    public StudentDTO(Long id,String firstname,String lastname,	Long group) {
    	this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.group = group;
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

	public Long getGroup() {
		return group;
	}

	public void setGroup(Long group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
}