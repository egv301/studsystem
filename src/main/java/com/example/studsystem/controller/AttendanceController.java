package com.example.studsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.studsystem.dto.AttendanceDTO;
import com.example.studsystem.dto.AttendanceSendDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.service.AttendanceService;

@RestController
@RequestMapping("/api/teacher")
public class AttendanceController {
	@Autowired
	AttendanceService attendanceService;
	
	@GetMapping("/getAttendanceList/{subject_id}/{date}")
	public ResponseEntity<List<AttendanceDTO>> getAttendanceList(
			@PathVariable("subject_id") Long subject_id,
			@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws NotFoundException{
		return ResponseEntity.ok(attendanceService.getAttendanceBySubjectAndDate(subject_id,date));
	}
	
	@PostMapping("/attendance")
    public ResponseEntity<?> recordAttendance(@RequestBody AttendanceSendDTO attendanceSendDTO) throws NotFoundException {
        attendanceService.saveAttendance(attendanceSendDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	
	
}