package com.example.studsystem.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.studsystem.models.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
	
	@Query("SELECT e FROM Group e WHERE e.title = :title")
	Group findByTitle(@Param("title") String title);
	
	@Query("SELECT e FROM Group e WHERE e.id <> :id AND e.title = :title")
	Group findByNotTitle(@Param("id") Long id,@Param("title") String title);
	
	
}
