package com.example.SpringBootProject4.repository;

import com.example.SpringBootProject4.model.FeesDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FeesDetailsRepository extends JpaRepository<FeesDetails, Long> {
    List<FeesDetails> findByStudentStudentIdAndCourseCourseId(Long studentId, Long courseId);
    List<FeesDetails> findByStudentStudentId(Long studentId);

}
