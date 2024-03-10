package com.example.studsystem.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.studsystem.dto.SubjectDTO;
import com.example.studsystem.dto.SubjectForUpdateDTO;
import com.example.studsystem.dto.TeacherDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.Subject;
import com.example.studsystem.models.User;
import com.example.studsystem.repo.SubjectRepository;

@Service
public class SubjectService{
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	UserService userService;
	
	public void addSubject(SubjectDTO subjectObj) throws NotFoundException{
		User teacher = userService.findUserById(subjectObj.getTeacher()).orElseThrow(()->new NotFoundException("Teacher not found"));
		subjectRepository.save(new Subject(subjectObj.getTitle(),teacher));
    }
	
	public List<TeacherDTO> getTeachers() {
		List<User> teacherListDb = userService.findUserByRole("ROLE_TEACHER");
		List<TeacherDTO> teachersList = teacherListDb.stream().map(teacher -> 
		new TeacherDTO(teacher.getId(), teacher.getFirstName(), teacher.getLastname())).collect(Collectors.toList());
		return teachersList;
	}
	
	public SubjectForUpdateDTO getSubjectForUpdate(Long subject_id) throws NotFoundException {
		List<TeacherDTO> teachersList = getTeachers();
		Subject subject = getSubject(subject_id);
		SubjectDTO subjectDto = new SubjectDTO(subject.getId(),subject.getTitle(),subject.getTeacher().getId());
		return new SubjectForUpdateDTO(subjectDto,teachersList);
	}
	
	public Subject getSubject(Long subject_id) throws NotFoundException {
    	Subject subject = subjectRepository.findById(subject_id).orElseThrow(()->new NotFoundException("Subject not found"));
    	return subject;
    }
    
    public List<Subject> getSubjectList(){
    	return subjectRepository.findAll();
    }
    
    public List<SubjectDTO> getTeachersSubjects(Principal principal){
    	User teacher = userService.findByUsername(principal.getName()).get();
    	
        List<SubjectDTO> subjectList = teacher
        								.getSubjects()
        								.stream()
								        .map(subject -> new SubjectDTO(subject.getId(), subject.getTitle()))
								        .collect(Collectors.toList());
        return subjectList;
    }
    
    public Subject updateSubject(SubjectDTO subjectObj) throws NotFoundException{
    	Subject subject = subjectRepository.findById(subjectObj.getId()).orElseThrow(()->new NotFoundException("Subject not found"));
    	User teacher = userService.findUserById(subjectObj.getTeacher()).orElseThrow(()->new NotFoundException("Teacher not found"));
    	subject.setTitle(subjectObj.getTitle());
    	subject.setTeacher(teacher);
    	System.out.println("teacher" + teacher);
    	subjectRepository.save(subject);
    	return subject;
    }
    
    public void deleteSubject(Long subject_id) throws NotFoundException {
    	Subject subject = subjectRepository.findById(subject_id).orElseThrow(()->new NotFoundException("Subject not found"));
    	subjectRepository.deleteById(subject_id);
    }
}