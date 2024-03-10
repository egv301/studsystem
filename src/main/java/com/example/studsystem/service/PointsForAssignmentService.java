package com.example.studsystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studsystem.dto.PointsForAssignmentFormDTO;
import com.example.studsystem.dto.PointsForAssignmentFormWrapperDTO;
import com.example.studsystem.dto.PointsForAssignmentsDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.PointsForAssignments;
import com.example.studsystem.models.Student;
import com.example.studsystem.models.SubjectAssignments;
import com.example.studsystem.repo.PointsForAssigmentRepository;

@Service
public class PointsForAssignmentService {
	@Autowired
	PointsForAssigmentRepository pointsForAssigmentRepository;
	@Autowired
	StudentService studentService;
	@Autowired
	SubjectAssignmentService subjectAssignmentService;
	
	
	public PointsForAssignmentFormWrapperDTO getPointsAssignment(Long assignment_id) throws NotFoundException {
		SubjectAssignments assignment = subjectAssignmentService.getSubjectAssignment(assignment_id);
		Set<Student> studentList = assignment.getSubject().getStudents();
		List<PointsForAssignments> pointsAssignments = pointsForAssigmentRepository.findBySubjectAssignment(assignment);
		PointsForAssignmentFormWrapperDTO pointsForAssignmentFormWrapperDTO = new PointsForAssignmentFormWrapperDTO();
		pointsForAssignmentFormWrapperDTO.setAssignmentId(assignment.getId());
		pointsForAssignmentFormWrapperDTO.setAssignmentTitle(assignment.getTitle());
		Map<Long, Integer> pointsMap = new HashMap<>();
		List<PointsForAssignmentFormDTO> assignmentPointsList = new ArrayList<>();
		for(PointsForAssignments pointsForAssignments : pointsAssignments){
			pointsMap.put(pointsForAssignments.getStudent().getId(), pointsForAssignments.getPoints());
		}
		System.out.println(pointsMap);
		for(Student student : studentList){
			if(pointsMap.containsKey(student.getId())) {
				assignmentPointsList.add(new PointsForAssignmentFormDTO(student.getId(),student.getFirstname(),student.getLastname(),pointsMap.get(student.getId())));
			}else {
				assignmentPointsList.add(new PointsForAssignmentFormDTO(student.getId(),student.getFirstname(),student.getLastname(),0));
			}
		}
		pointsForAssignmentFormWrapperDTO.setPointsAssignmentList(assignmentPointsList);
		return pointsForAssignmentFormWrapperDTO;
	}
	
	public void addOrUpdatePointsForAssignment(PointsForAssignmentsDTO pointsForAssignmentObj) throws NotFoundException {
		Student student = studentService.getStudent(pointsForAssignmentObj.getStudent());
		SubjectAssignments subjectAssignments = subjectAssignmentService.getSubjectAssignment(pointsForAssignmentObj.getSubjectAssignment());
		PointsForAssignments pointsForAssignments = pointsForAssigmentRepository.findByStudentAndSubjectAssignment(student, subjectAssignments).orElse(null);
		if(pointsForAssignments!=null) {
			pointsForAssignments.setPoints(pointsForAssignmentObj.getPoints());
			pointsForAssigmentRepository.save(pointsForAssignments);
		}else {
			PointsForAssignments newPointsForAssignments = new PointsForAssignments(student,subjectAssignments,pointsForAssignmentObj.getPoints());
			pointsForAssigmentRepository.save(newPointsForAssignments);
		}
	}

	
}
