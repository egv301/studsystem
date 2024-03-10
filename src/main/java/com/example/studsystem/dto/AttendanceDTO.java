package com.example.studsystem.dto;

import java.time.LocalDate;

public class AttendanceDTO {
	private Long studentId;
    private String firstName;
    private String lastName;
    private boolean attended;
    private LocalDate date;
	
	public AttendanceDTO(){}
	
	public AttendanceDTO(Long studentId, String firstName, String lastName, boolean attended,LocalDate date) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.attended = attended;
		this.date = date;
	}
	
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean isAttended() {
		return attended;
	}
	public void setAttended(boolean attended) {
		this.attended = attended;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	

    // Constructors, Getters, and Setters
	
}