package com.example.studsystem.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.studsystem.dto.ShowStudentSubjectDTO;
import com.example.studsystem.dto.StudentSubjectDTO;
import com.example.studsystem.dto.SubjectDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.Student;
import com.example.studsystem.models.Subject;
import com.example.studsystem.repo.StudentRepository;

@Service
public class StudentSubjectService {
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final StudentRepository studentRepository;

    public StudentSubjectService(
            StudentService studentService,
            SubjectService subjectService,
            StudentRepository studentRepository) {
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.studentRepository = studentRepository;
    }

	public ShowStudentSubjectDTO checkStudentsSubject(Long student_id) throws NotFoundException {
		ShowStudentSubjectDTO showStudentSubjectDTO = new ShowStudentSubjectDTO();
		List<Subject> subjectList = subjectService.getSubjectList();
		Set<Subject> studentSubjectsList = studentService.getSubjectList(student_id);
		for (Subject subject : subjectList) {
			if(studentSubjectsList.contains(subject)) {
				showStudentSubjectDTO.setStudent(student_id);
				showStudentSubjectDTO.getStudentsSubject().add(new SubjectDTO(subject.getId(), subject.getTitle()));
			}else {
				showStudentSubjectDTO.setStudent(student_id);
				showStudentSubjectDTO.getSubjectsToRegister().add(new SubjectDTO(subject.getId(), subject.getTitle()));
			}
			
		}
		return showStudentSubjectDTO;
	}
	
	public void addSubjectToStudent(StudentSubjectDTO studentSubjectDTO) throws NotFoundException {
		Student student = studentService.getStudent(studentSubjectDTO.getStudent());
		Subject subject = subjectService.getSubject(studentSubjectDTO.getSubject());
		if (!student.getSubjects().contains(subject)) {
			student.getSubjects().add(subject);
			subject.getStudents().add(student);
			studentRepository.save(student);
		}
	}
	
	public void removeSubjectFromStudent(StudentSubjectDTO studentSubjectDTO) throws NotFoundException {
		Student student = studentService.getStudent(studentSubjectDTO.getStudent());
		Subject subject = subjectService.getSubject(studentSubjectDTO.getSubject());
		student.getSubjects().remove(subject);
		studentRepository.save(student);
	}
}
