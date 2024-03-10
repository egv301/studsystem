package com.example.studsystem.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import java.security.Principal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.studsystem.dto.SubjectDTO;
import com.example.studsystem.dto.SubjectForUpdateDTO;
import com.example.studsystem.dto.TeacherDTO;

import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.Subject;
import com.example.studsystem.models.User;
import com.example.studsystem.service.SubjectService;
import com.example.studsystem.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class SubjectController {
	@Autowired
	SubjectService subjectService;

	@Autowired
	UserService userService;

	@GetMapping("/subject-list")
	public ResponseEntity<List<Subject>> subjectList(){
		return ResponseEntity.ok(subjectService.getSubjectList());
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
	public ResponseEntity<Subject> addSubject(@RequestBody @Valid SubjectDTO subjectObj) throws NotFoundException{
		System.out.println(subjectObj);
		subjectService.addSubject(subjectObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update-subject")
	public ResponseEntity<Subject> updateSubject(@RequestBody @Valid SubjectDTO subjectObj) throws NotFoundException{
		System.out.println(subjectObj);
		subjectService.updateSubject(subjectObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-subject/{subject_id}")
	public ResponseEntity<Subject> deleteSubject(@PathVariable("subject_id") Long subject_id) throws NotFoundException {
		subjectService.deleteSubject(subject_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
