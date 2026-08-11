package com.example.studsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.studsystem.dto.SubjectAssignmentDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.service.SubjectAssignmentService;

@RestController
@RequestMapping("/api/teacher")
public class SubjectAssignmentController {
    private final SubjectAssignmentService subjectAssignmentService;

    public SubjectAssignmentController(SubjectAssignmentService subjectAssignmentService) {
        this.subjectAssignmentService = subjectAssignmentService;
    }

	@GetMapping("/assignment-list/{subject_id}")
	public ResponseEntity<List<SubjectAssignmentDTO>> subjectAssignmentList(@PathVariable("subject_id") Long subject_id) throws NotFoundException{
		return ResponseEntity.ok(subjectAssignmentService.subjectAssignmentListDTO(subject_id));
	}
	
	@GetMapping("/assignment/{assignment_id}")
	public ResponseEntity<SubjectAssignmentDTO> getAssignment(@PathVariable("assignment_id") Long assignment_id) throws NotFoundException{
		return ResponseEntity.ok(subjectAssignmentService.getSubjectAssignmentDTO(assignment_id));
	}
	
	@PostMapping("/add-assignment")
	public ResponseEntity<?> addAssignment(@RequestBody @Valid SubjectAssignmentDTO subjectAssignmentObj) throws NotFoundException{
		subjectAssignmentService.addSubjectAssignment(subjectAssignmentObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update-assignment")
	public ResponseEntity<?> updateAssignment(@RequestBody @Valid SubjectAssignmentDTO subjectAssignmentObj) throws NotFoundException{
		subjectAssignmentService.updateSubjectAssignment(subjectAssignmentObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-assignment/{subjectAssignment_id}")
	public ResponseEntity<?> deleteAssignment(@PathVariable("subjectAssignment_id") Long subjectAssignment_id) throws NotFoundException{
		subjectAssignmentService.deleteSubjectAssignment(subjectAssignment_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
