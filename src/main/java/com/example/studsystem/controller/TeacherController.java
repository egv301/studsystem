package com.example.studsystem.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studsystem.dto.SubjectDTO;
import com.example.studsystem.service.SubjectService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	@Autowired
	SubjectService subjectService;

	@GetMapping("/teachers-subjects")
	public ResponseEntity<List<SubjectDTO>> teachersSubjects(Principal principal){
		return ResponseEntity.ok(subjectService.getTeachersSubjects(principal));
	}
}
