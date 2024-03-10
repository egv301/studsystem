package com.example.studsystem.service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studsystem.dto.SubjectAssignmentDTO;
import com.example.studsystem.dto.SubjectDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.Subject;
import com.example.studsystem.models.SubjectAssignments;
import com.example.studsystem.models.User;
import com.example.studsystem.repo.PointsForAssigmentRepository;
import com.example.studsystem.repo.SubjectAssignmentRepository;

@Service
public class SubjectAssignmentService{
	
	@Autowired
	UserService userService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	SubjectAssignmentRepository subjectAssignmentRepository;
	
	public List<SubjectAssignments> subjectAssignmentsList(){
		return subjectAssignmentRepository.findAll();
	}
	
	public void addSubjectAssigment(SubjectAssignmentDTO subjectAssigmentObj) throws NotFoundException{
		Subject subject = subjectService.getSubject(subjectAssigmentObj.getSubject());
		subjectAssignmentRepository.save(new SubjectAssignments(subjectAssigmentObj.getTitle(), subject));
    }
    
    public SubjectAssignmentDTO getSubjectAssignmentDTO(Long subjectAssignment_id) throws NotFoundException {
    	SubjectAssignments subjectAssignment = subjectAssignmentRepository.findById(subjectAssignment_id).orElseThrow(()->new NotFoundException("Assignment not found"));
    	return new SubjectAssignmentDTO(subjectAssignment.getId(), subjectAssignment.getTitle(),subjectAssignment.getSubject().getId());
    }
    
    public SubjectAssignments getSubjectAssignment(Long subjectAssignment_id) throws NotFoundException {
    	SubjectAssignments subjectAssignment = subjectAssignmentRepository.findById(subjectAssignment_id).orElseThrow(()->new NotFoundException("Assignment not found"));
    	return subjectAssignment;
    }
    
    public List<SubjectAssignmentDTO> subjectAssigmentListDTO(Long subject_id) throws NotFoundException{
    	Subject subject = subjectService.getSubject(subject_id);
    	
        List<SubjectAssignmentDTO> assignmentList = subject
        											.getAssignmentsList()
        											.stream()
        											.map(assignment -> new SubjectAssignmentDTO(assignment.getId(), assignment.getTitle()))
        											.collect(Collectors.toList());
        return assignmentList;
    	
    }
    
    public List<SubjectAssignments> subjectAssigmentList(Long subject_id) throws NotFoundException{
    	Subject subject = subjectService.getSubject(subject_id);
    	return subjectAssignmentRepository.findBySubject(subject);
    }
    
    public void updateSubjectAssigment(SubjectAssignmentDTO subjectAssigmentObj) throws NotFoundException {
    	SubjectAssignments subjectAssigment = subjectAssignmentRepository.findById(subjectAssigmentObj.getId()).orElseThrow(()->new NotFoundException("Assignment not found"));
    	subjectAssigment.setTitle(subjectAssigmentObj.getTitle());
    	subjectAssignmentRepository.save(subjectAssigment);
    }
    
    public void deleteSubjectAssigment(Long subjectAssigment_id) throws NotFoundException {
    	SubjectAssignments subjectAssigment = subjectAssignmentRepository.findById(subjectAssigment_id).orElseThrow(()->new NotFoundException("Assignment not found"));
    	subjectAssignmentRepository.deleteById(subjectAssigment_id);
    }
}