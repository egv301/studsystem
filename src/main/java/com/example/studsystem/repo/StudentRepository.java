package com.example.studsystem.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.studsystem.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
   @Query(value = "SELECT * FROM STUDENTS WHERE GROUP_ID = :group_id",nativeQuery = true)	
   public List<Student> findStudentsByGroupId(@Param("group_id") Long group_id);
   
   @Query("SELECT s FROM Student s WHERE s.firstname LIKE %:word% OR s.lastname LIKE %:word%")
   List<Student> findByFirstNameContainsOrLastNameContains(@Param("word") String word);
   
}
