package com.example.studsystem.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.studsystem.models.PointsForAssignments;
import com.example.studsystem.models.Student;
import com.example.studsystem.models.SubjectAssignments;

public interface PointsForAssigmentRepository extends JpaRepository<PointsForAssignments, Long> {
	//@Query(nativeQuery = true,value="SELECT * FROM points_for_assignments where student_id = :st_id and assignment_id = :assign_id limit 1")
//	@Query("SELECT p FROM PointsForAssignment p WHERE p.student = :st_id AND p.subjectAssignment = :assign_id")
	//PointsForAssignments checkPoints(@Param("st_id") Long student_id, @Param("assign_id") Long assignment_id);
//	PointsForAssignments findByStudentAndAssignment(Student student, SubjectAssignments subjectAssignments);
	Optional<PointsForAssignments> findByStudentAndSubjectAssignment(Student student, SubjectAssignments assignment);
	
	List<PointsForAssignments> findBySubjectAssignment(SubjectAssignments subjectAssignments);
}
