package com.example.studsystem.dto;

import java.util.List;

public class SubjectForUpdateDTO {
	
	private Long id;
	private SubjectDTO subject;
    private List<TeacherDTO> teachersList;

	public SubjectForUpdateDTO(){}

    public SubjectForUpdateDTO(SubjectDTO subjectDto,List<TeacherDTO> teachersList){
        this.subject = subjectDto;
        this.teachersList = teachersList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subjectDto) {
        this.subject = subjectDto;
    }

    public List<TeacherDTO> getTeachersList() {
        return teachersList;
    }

    public void setTeachersList(List<TeacherDTO> teachersList) {
        this.teachersList = teachersList;
    }
}
