package com.example.studsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.studsystem.dto.SubjectAssignmentDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.Subject;
import com.example.studsystem.models.SubjectAssignments;
import com.example.studsystem.repo.SubjectAssignmentRepository;

@Service
public class SubjectAssignmentService{
	
    private final SubjectService subjectService;
    private final SubjectAssignmentRepository subjectAssignmentRepository;

    public SubjectAssignmentService(
            SubjectService subjectService,
            SubjectAssignmentRepository subjectAssignmentRepository) {
        this.subjectService = subjectService;
        this.subjectAssignmentRepository = subjectAssignmentRepository;
    }
	
	public List<SubjectAssignments> subjectAssignmentsList(){
		return subjectAssignmentRepository.findAll();
	}
	
	public void addSubjectAssignment(SubjectAssignmentDTO subjectAssignmentObj) throws NotFoundException{
		Subject subject = subjectService.getSubject(subjectAssignmentObj.getSubject());
		subjectAssignmentRepository.save(new SubjectAssignments(subjectAssignmentObj.getTitle(), subject));
    }
    
    public SubjectAssignmentDTO getSubjectAssignmentDTO(Long subjectAssignment_id) throws NotFoundException {
    	SubjectAssignments subjectAssignment = subjectAssignmentRepository.findById(subjectAssignment_id).orElseThrow(()->new NotFoundException("Assignment not found"));
    	return new SubjectAssignmentDTO(subjectAssignment.getId(), subjectAssignment.getTitle(),subjectAssignment.getSubject().getId());
    }
    
    public SubjectAssignments getSubjectAssignment(Long subjectAssignment_id) throws NotFoundException {
    	SubjectAssignments subjectAssignment = subjectAssignmentRepository.findById(subjectAssignment_id).orElseThrow(()->new NotFoundException("Assignment not found"));
    	return subjectAssignment;
    }
    
    public List<SubjectAssignmentDTO> subjectAssignmentListDTO(Long subject_id) throws NotFoundException{
    	Subject subject = subjectService.getSubject(subject_id);
    	
        List<SubjectAssignmentDTO> assignmentList = subject
        											.getAssignmentsList()
        											.stream()
        											.map(assignment -> new SubjectAssignmentDTO(assignment.getId(), assignment.getTitle()))
        											.collect(Collectors.toList());
        return assignmentList;
    	
    }
    
    public List<SubjectAssignments> subjectAssignmentList(Long subject_id) throws NotFoundException{
    	Subject subject = subjectService.getSubject(subject_id);
    	return subjectAssignmentRepository.findBySubject(subject);
    }
    
    public void updateSubjectAssignment(SubjectAssignmentDTO subjectAssignmentObj) throws NotFoundException {
        SubjectAssignments subjectAssignment = subjectAssignmentRepository.findById(subjectAssignmentObj.getId()).orElseThrow(()->new NotFoundException("Assignment not found"));
        subjectAssignment.setTitle(subjectAssignmentObj.getTitle());
        subjectAssignmentRepository.save(subjectAssignment);
    }
    
    public void deleteSubjectAssignment(Long subjectAssignment_id) throws NotFoundException {
        subjectAssignmentRepository.findById(subjectAssignment_id).orElseThrow(()->new NotFoundException("Assignment not found"));
        subjectAssignmentRepository.deleteById(subjectAssignment_id);
    }
}
