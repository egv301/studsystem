package com.example.studsystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studsystem.dto.AttendanceDTO;
import com.example.studsystem.dto.AttendanceSendDTO;
import com.example.studsystem.exceptions.NotFoundException;
import com.example.studsystem.models.Attendance;
import com.example.studsystem.models.Student;
import com.example.studsystem.models.Subject;
import com.example.studsystem.repo.AttendanceRepository;
import com.example.studsystem.repo.SubjectRepository;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private SubjectService subjectService;

    public List<AttendanceDTO> getAttendanceBySubjectAndDate(Long subjectId, LocalDate date) throws NotFoundException {
        List<Attendance> attendances = attendanceRepository.findBySubjectIdAndDate(subjectId, date);
        Subject subject = subjectService.getSubject(subjectId);

        if (attendances.isEmpty()) {
            // If no attendance records, assume all enrolled students did not attend
            return subject.getStudents().stream()
                    .map(student -> new AttendanceDTO(student.getId(), student.getFirstname(), student.getLastname(), false,date))
                    .collect(Collectors.toList());
        } else {
            // Process found records and include logic to handle students without attendance records
            List<AttendanceDTO> results = new ArrayList<>();
            Map<Long, Attendance> attendanceMap = attendances.stream()
                    .collect(Collectors.toMap(a -> a.getStudent().getId(), a -> a));

            subject.getStudents().forEach(student -> {
                boolean attended = Optional.ofNullable(attendanceMap.get(student.getId()))
                                           .map(Attendance::isAttended)
                                           .orElse(false);
                results.add(new AttendanceDTO(student.getId(), student.getFirstname(), student.getLastname(), attended,date));
            });
            
            return results;
        }
    }
    
    public void saveAttendance(AttendanceSendDTO attendanceSendDTO) throws NotFoundException {
        Student student = studentService.getStudent(attendanceSendDTO.getStudent());
        Subject subject = subjectService.getSubject(attendanceSendDTO.getSubject());
                

        Attendance attendance = attendanceRepository
                .findByStudentAndSubjectAndDate(student, subject, attendanceSendDTO.getDate())
                .orElse(new Attendance());

        attendance.setStudent(student);
        attendance.setSubject(subject);
        attendance.setDate(attendanceSendDTO.getDate());
        attendance.setAttended(attendanceSendDTO.isAttended());

        attendanceRepository.save(attendance);
    }
}
