package com.example.studsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.studsystem.dto.ShowStudentSubjectDTO;
import com.example.studsystem.dto.StudentSubjectDTO;
import com.example.studsystem.dto.SubjectDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.service.StudentService;
import com.example.studsystem.service.StudentSubjectService;

@RestController
@RequestMapping("/api/admin")
public class StudentSubjectController {
	
	@Autowired
	StudentSubjectService studentSubjectService;
	
	@GetMapping("/getStudentsSubjects/{student_id}")
	public ResponseEntity<ShowStudentSubjectDTO> getStudentsSubjects(@PathVariable("student_id") Long student_id) throws NotFoundException {
		return ResponseEntity.ok(studentSubjectService.checkStudentsSubject(student_id));
	}
	
	@PostMapping("/addSubjectToStudent")
	public ResponseEntity<?> addSubjectToStudent(@RequestBody @Valid StudentSubjectDTO studentSubjectDto ) throws NotFoundException{
		studentSubjectService.addSubjectToStudent(studentSubjectDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/removeSubjectFromStudent")
	public ResponseEntity<?> removeSubjectFromStudent(@RequestBody @Valid StudentSubjectDTO studentSubjectDto ) throws NotFoundException{
		studentSubjectService.removeSubjectFromStudent(studentSubjectDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
