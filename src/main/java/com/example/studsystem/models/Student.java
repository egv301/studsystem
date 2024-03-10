package com.example.studsystem.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "students")
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Size(min=2, message = "At least 2 symbols")
    @Column(name="firstname")
    private String firstname;
    
    @Size(min=2, message = "At least 2 symbols")
    @Column(name="lastname")
    private String lastname;
    
    @ManyToOne()
    @JoinColumn(name="group_id")
    private Group group;
    
    @ManyToMany()
    @JoinTable(name = "students_subjects",
            joinColumns =  @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<>();
    
    public Student() {
    }
    
    public Student(String firstname,String lastname,Group group) {
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
	
	
	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
	
	

	
    
   

}