package com.example.studsystem.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.studsystem.dto.SubjectAssignmentDTO;
import com.example.studsystem.dto.SubjectDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.Subject;
import com.example.studsystem.models.SubjectAssignments;
import com.example.studsystem.service.SubjectAssignmentService;
import com.example.studsystem.service.SubjectService;

@RestController
@RequestMapping("/api/teacher")
public class SubjectAssignmentController {
	@Autowired
	SubjectAssignmentService subjectAssignmentService;
	
	@Autowired
	SubjectService subjectService;

	@GetMapping("/assignment-list/{subject_id}")
	public ResponseEntity<List<SubjectAssignmentDTO>> subjectAssignmentList(@PathVariable("subject_id") Long subject_id) throws NotFoundException{
		return ResponseEntity.ok(subjectAssignmentService.subjectAssigmentListDTO(subject_id));
	}
	
	@GetMapping("/assignment/{assignment_id}")
	public ResponseEntity<SubjectAssignmentDTO> getAssignment(@PathVariable("assignment_id") Long assignment_id) throws NotFoundException{
		return ResponseEntity.ok(subjectAssignmentService.getSubjectAssignmentDTO(assignment_id));
	}
	
	@PostMapping("/add-assignment")
	public ResponseEntity<?> addAssignment(@RequestBody @Valid SubjectAssignmentDTO subjectAssignmentObj) throws NotFoundException{
		subjectAssignmentService.addSubjectAssigment(subjectAssignmentObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update-assignment")
	public ResponseEntity<?> updateAssignment(@RequestBody @Valid SubjectAssignmentDTO subjectAssignmentObj) throws NotFoundException{
		subjectAssignmentService.updateSubjectAssigment(subjectAssignmentObj);
		System.out.println(subjectAssignmentObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-assignment/{subjectAssignment_id}")
	public ResponseEntity<?> deleteAssignment(@PathVariable("subjectAssignment_id") Long subjectAssignment_id) throws NotFoundException{
		subjectAssignmentService.deleteSubjectAssigment(subjectAssignment_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
