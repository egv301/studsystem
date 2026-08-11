package com.example.studsystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.studsystem.dto.PointsForAssignmentFormDTO;
import com.example.studsystem.dto.PointsForAssignmentFormWrapperDTO;
import com.example.studsystem.dto.PointsForAssignmentsDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.PointsForAssignments;
import com.example.studsystem.models.Student;
import com.example.studsystem.models.SubjectAssignments;
import com.example.studsystem.repo.PointsForAssignmentRepository;

@Service
public class PointsForAssignmentService {
    private final PointsForAssignmentRepository pointsForAssignmentRepository;
    private final StudentService studentService;
    private final SubjectAssignmentService subjectAssignmentService;

    public PointsForAssignmentService(
            PointsForAssignmentRepository pointsForAssignmentRepository,
            StudentService studentService,
            SubjectAssignmentService subjectAssignmentService) {
        this.pointsForAssignmentRepository = pointsForAssignmentRepository;
        this.studentService = studentService;
        this.subjectAssignmentService = subjectAssignmentService;
    }
	
	
	public PointsForAssignmentFormWrapperDTO getPointsAssignment(Long assignment_id) throws NotFoundException {
		SubjectAssignments assignment = subjectAssignmentService.getSubjectAssignment(assignment_id);
		Set<Student> studentList = assignment.getSubject().getStudents();
		List<PointsForAssignments> pointsAssignments = pointsForAssignmentRepository.findBySubjectAssignment(assignment);
		PointsForAssignmentFormWrapperDTO pointsForAssignmentFormWrapperDTO = new PointsForAssignmentFormWrapperDTO();
		pointsForAssignmentFormWrapperDTO.setAssignmentId(assignment.getId());
		pointsForAssignmentFormWrapperDTO.setAssignmentTitle(assignment.getTitle());
		Map<Long, Integer> pointsMap = new HashMap<>();
		List<PointsForAssignmentFormDTO> assignmentPointsList = new ArrayList<>();
		for(PointsForAssignments pointsForAssignments : pointsAssignments){
			pointsMap.put(pointsForAssignments.getStudent().getId(), pointsForAssignments.getPoints());
		}
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
		PointsForAssignments pointsForAssignments = pointsForAssignmentRepository.findByStudentAndSubjectAssignment(student, subjectAssignments).orElse(null);
		if(pointsForAssignments!=null) {
			pointsForAssignments.setPoints(pointsForAssignmentObj.getPoints());
			pointsForAssignmentRepository.save(pointsForAssignments);
		}else {
			PointsForAssignments newPointsForAssignments = new PointsForAssignments(student,subjectAssignments,pointsForAssignmentObj.getPoints());
			pointsForAssignmentRepository.save(newPointsForAssignments);
		}
	}

	
}
