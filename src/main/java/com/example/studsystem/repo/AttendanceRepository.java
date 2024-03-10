package com.example.studsystem.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studsystem.models.Attendance;
import com.example.studsystem.models.Student;
import com.example.studsystem.models.Subject;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
	List<Attendance> findBySubjectIdAndDate(Long subjectId, LocalDate date);
    Optional<Attendance> findByStudentAndSubjectAndDate(Student student, Subject subject, LocalDate date);
} 