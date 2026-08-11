package com.example.studsystem.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studsystem.dto.PointsForAssignmentFormWrapperDTO;
import com.example.studsystem.dto.PointsForAssignmentsDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.service.PointsForAssignmentService;


@RestController
@RequestMapping("/api/teacher")
public class PointsForAssignmentController {
    private final PointsForAssignmentService pointsForAssignmentService;

    public PointsForAssignmentController(PointsForAssignmentService pointsForAssignmentService) {
        this.pointsForAssignmentService = pointsForAssignmentService;
    }
	
	@GetMapping("/getPointsList/{assignment_id}")
	public ResponseEntity<PointsForAssignmentFormWrapperDTO> getPointsAssignment(@PathVariable("assignment_id") Long assignment_id) throws NotFoundException{
		return ResponseEntity.ok(pointsForAssignmentService.getPointsAssignment(assignment_id));
	}
	
	@PostMapping("/addPointsToAssignment")
	public ResponseEntity<?> addPoints(@RequestBody @Valid PointsForAssignmentsDTO pointsForAssignmentObj) throws NotFoundException {
		pointsForAssignmentService.addOrUpdatePointsForAssignment(pointsForAssignmentObj);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
