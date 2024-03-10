package com.example.studsystem.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studsystem.dto.GroupDTO;
import com.example.studsystem.dto.StudentDTO;
import com.example.studsystem.dto.StudentForUpdateDTO;
import com.example.studsystem.dto.StudentSubjectDTO;
import com.example.studsystem.dto.SubjectDTO;
import com.example.studsystem.dto.SubjectForUpdateDTO;
import com.example.studsystem.dto.TeacherDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.Group;
import com.example.studsystem.models.Student;
import com.example.studsystem.models.Subject;
import com.example.studsystem.models.User;
import com.example.studsystem.repo.StudentRepository;
import com.example.studsystem.repo.SubjectRepository;

@Service
public class StudentService{
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	GroupService groupService;
	
    public void addStudent(StudentDTO studentObj) throws NotFoundException {
    	Group group = groupService.getGroup(studentObj.getGroup());
    	if(group==null) {
    		throw new NotFoundException("Group was not found");
    	}
    	studentRepository.save(new Student(studentObj.getFirstname(),studentObj.getLastname(),group));
    }
    
    public Student getStudent(Long student_id) throws NotFoundException {
    	Student student = studentRepository.findById(student_id).orElseThrow(()->new NotFoundException("Student not found"));
    	return student;
    }
    
    public StudentForUpdateDTO getStudentForUpdate(Long student_id) throws NotFoundException {
    	List<GroupDTO> groupsList = getGroupsForStudent();
		Student student = getStudent(student_id);
		StudentDTO studentDto = new StudentDTO(student.getId(),student.getFirstname(),student.getLastname(),student.getGroup().getId());
		return new StudentForUpdateDTO(studentDto, groupsList);
	}
    
    public List<GroupDTO> getGroupsForStudent(){
    	List<Group> groupListDb = groupService.groupList();
    	List<GroupDTO> groupList = groupListDb.stream().map(
    			group -> new GroupDTO(group.getId(), group.getTitle()))
    		    .collect(Collectors.toList());
    	return groupList;
    }
    
    public List<StudentDTO> studentListDTO(){
    	List<Student> studentsList = studentRepository.findAll();
    	List<StudentDTO> studentListdto = studentsList.stream().map(
    			student -> new StudentDTO(student.getId(),student.getFirstname(),student.getLastname(),student.getGroup().getId()))
    			.collect(Collectors.toList());
    	return studentListdto;
    }
    
    public List<Student> studentList(){
    	return studentRepository.findAll();
    }
    
    public List<Student> getStudentsByGroup(Long group_id){
    	return studentRepository.findStudentsByGroupId(group_id);
    }
    
    public Set<Subject> getSubjectList(Long student_id) throws NotFoundException{
    	Student student = studentRepository.findById(student_id).orElseThrow(()->new NotFoundException("Student not found"));
    	return student.getSubjects();
    	
    }
    
    
    public Student updateStudent(StudentDTO studentObj) throws NotFoundException {
    	Student student = studentRepository.findById(studentObj.getId()).orElseThrow(()->new NotFoundException("Student not found"));
    	Group group = groupService.getGroup(studentObj.getGroup());
    	student.setFirstname(studentObj.getFirstname());
    	student.setLastname(studentObj.getLastname());
    	student.setGroup(group);
    	studentRepository.save(student);
    	return student;
    }
    
    public void deleteStudent(Long student_id) throws NotFoundException {
    	Student student = studentRepository.findById(student_id).orElseThrow(()->new NotFoundException("Student not found"));
    	studentRepository.deleteById(student_id);;
    }
    
    public void addSubject(Long student_id,StudentSubjectDTO studentSubjectDto) throws NotFoundException {
    	Student student = studentRepository.findById(student_id).orElseThrow(()->new NotFoundException("Student not found"));
    	Subject subject = subjectService.getSubject(studentSubjectDto.getSubject());
        student.getSubjects().add(subject);
        //subject.getStudents().add(student);
        studentRepository.save(student);
    }

    public void removeSubject(Long student_id,Long subject_id) throws NotFoundException {
    	Student student = studentRepository.findById(student_id).orElseThrow(()->new NotFoundException("Student not found"));
    	Subject subject = subjectService.getSubject(subject_id);
        student.getSubjects().remove(subject);
       // subject.getStudents().remove(student);
        studentRepository.save(student);
    }
}