package com.example.studsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studsystem.dto.GroupDTO;
import com.example.studsystem.dto.StudentDTO;
import com.example.studsystem.dto.StudentForUpdateDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.exceptions.StudentExistsException;
//import com.example.studsystem.models.Group;
import com.example.studsystem.models.Student;
import com.example.studsystem.service.GroupService;
import com.example.studsystem.service.StudentService;

@RestController
@RequestMapping("/api/admin")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	GroupService groupService;

	@GetMapping("/student-list")
	public ResponseEntity<List<StudentDTO>> studentList(){
		return ResponseEntity.ok(studentService.studentListDTO());
	}
	
	@GetMapping("/student-list/{group_id}")
	public ResponseEntity<List<Student>> studentsByGroup(@PathVariable("group_id") Long group_id){
		return ResponseEntity.ok(studentService.getStudentsByGroup(group_id));
	}
	
	@GetMapping("/showAddStudentForm")
	public ResponseEntity<List<GroupDTO>> showAddStudentForm(){
		return ResponseEntity.ok(studentService.getGroupsForStudent());
	}
	
	@GetMapping("/showUpdateStudentForm/{student_id}")
	public ResponseEntity<StudentForUpdateDTO> showUpdateStudentForm(@PathVariable("student_id") Long student_id) throws NotFoundException{
		return ResponseEntity.ok(studentService.getStudentForUpdate(student_id));
	}
	
	@GetMapping("/student/{student_id}")
	public ResponseEntity<Student> getStudent(@PathVariable("student_id") Long student_id) throws NotFoundException{
		return ResponseEntity.ok(studentService.getStudent(student_id));
	}
	
	@PostMapping("/add-student")
	public ResponseEntity<Student> addStudent(@RequestBody @Valid StudentDTO studentObj) throws StudentExistsException, NotFoundException{
		studentService.addStudent(studentObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update-student")
	public ResponseEntity<Student> updateStudent(@RequestBody @Valid StudentDTO studentObj) throws NotFoundException,StudentExistsException{
		studentService.updateStudent(studentObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-student/{student_id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("student_id") Long student_id) throws NotFoundException {
		studentService.deleteStudent(student_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
