package com.example.studsystem.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studsystem.models.Subject;
import com.example.studsystem.models.SubjectAssignments;

public interface SubjectAssignmentRepository extends JpaRepository<SubjectAssignments, Long> {
	List<SubjectAssignments> findBySubject(Subject subject);
}
