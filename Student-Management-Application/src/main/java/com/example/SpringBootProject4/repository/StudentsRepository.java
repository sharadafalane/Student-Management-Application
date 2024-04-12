package com.example.SpringBootProject4.repository;

import com.example.SpringBootProject4.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

}
