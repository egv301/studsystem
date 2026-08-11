package com.example.studsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.studsystem.dto.SubjectDTO;
import com.example.studsystem.dto.SubjectForUpdateDTO;
import com.example.studsystem.dto.TeacherDTO;

import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.service.SubjectService;

@RestController
@RequestMapping("/api/admin")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

	@GetMapping("/subject-list")
	public ResponseEntity<List<SubjectDTO>> subjectList(){
		return ResponseEntity.ok(subjectService.getSubjectListDTO());
	}
	
	@GetMapping("/showAddSubjectForm")
	public ResponseEntity<List<TeacherDTO>> showAddSubjectForm(){
		return ResponseEntity.ok(subjectService.getTeachers());
	}
	
	@GetMapping("/subject/{subject_id}")
	public ResponseEntity<SubjectForUpdateDTO> getSubject(@PathVariable("subject_id") Long subject_id) throws NotFoundException{
		return ResponseEntity.ok(subjectService.getSubjectForUpdate(subject_id));
	}
	
	@PostMapping("/add-subject")
	public ResponseEntity<?> addSubject(@RequestBody @Valid SubjectDTO subjectObj) throws NotFoundException{
		subjectService.addSubject(subjectObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update-subject")
	public ResponseEntity<?> updateSubject(@RequestBody @Valid SubjectDTO subjectObj) throws NotFoundException{
		subjectService.updateSubject(subjectObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-subject/{subject_id}")
	public ResponseEntity<?> deleteSubject(@PathVariable("subject_id") Long subject_id) throws NotFoundException {
		subjectService.deleteSubject(subject_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
