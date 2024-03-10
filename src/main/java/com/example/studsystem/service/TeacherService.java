//package com.example.studsystem.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.studsystem.dto.TeacherDTO;
//import com.example.studsystem.exceptions.NotFoundException;
//import com.example.studsystem.models.Teacher;
//import com.example.studsystem.repo.TeacherRepository;
//
//@Service
//public class TeacherService{
//	@Autowired
//	TeacherRepository teacherRepository;
//	
//	@Autowired
//	GroupService groupService;
//	
//    public void addTeacher(TeacherDTO teacherObj){
//    	teacherRepository.save(new Teacher(teacherObj.getFirstname(),teacherObj.getLastname()));
//    }
//    
//    public Teacher getTeacher(Long teacher_id) throws NotFoundException {
//    	Teacher teacher = teacherRepository.findById(teacher_id).orElse(null);
//    	if(teacher==null) {
//    		throw new NotFoundException("Teacher not found");
//    	}
//    	return teacher;
//    }
//    
//    public List<Teacher> teacherList(){
//    	return teacherRepository.findAll();
//    }
//    
//    public Teacher updateTeacher(TeacherDTO teacherObj) throws NotFoundException {
//    	Teacher teacher = teacherRepository.findById(teacherObj.getId()).orElse(null);
//    	if(teacher==null) {
//    		throw new NotFoundException("Teacher not found");
//    	}
//    	
//    	teacher.setFirstname(teacherObj.getFirstname());
//    	teacher.setLastname(teacherObj.getLastname());
//    	teacherRepository.save(teacher);
//    	return teacher;
//    }
//    
//    public void deleteTeacher(Long teacher_id) throws NotFoundException {
//    	Teacher teacher = teacherRepository.findById(teacher_id).orElse(null);
//    	if(teacher==null) {
//    		throw new NotFoundException("Teacher not found");
//    	}
//    	teacherRepository.deleteById(teacher_id);
//    }
//}