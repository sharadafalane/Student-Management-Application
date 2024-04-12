package com.example.SpringBootProject4.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.SpringBootProject4.model.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
 
}