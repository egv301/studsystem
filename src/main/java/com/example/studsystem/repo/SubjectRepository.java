package com.example.studsystem.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studsystem.models.Subject;
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
}
