package com.example.SpringBootProject4.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.SpringBootProject4.model.StudentCourses;
import com.example.SpringBootProject4.dto.StudentCoursesDTO;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentCoursesRepository extends JpaRepository<StudentCourses, Long> {

	  @Query("SELECT new com.example.SpringBootProject4.dto.StudentCoursesDTO(s.studentId, s.firstName, s.lastName, c.courseName, sc.studScore) " +
	           "FROM Students s " +
	           "JOIN s.coursesCompleted sc " +
	           "JOIN sc.course c " +
	           "WHERE s.studentId = :studentId")
	 List<StudentCoursesDTO> getStudentCoursesDetails(@Param("studentId") Long studentId);
	    
	  // @Query("SELECT COUNT(sc) FROM StudentCourses sc WHERE sc.course.id = :courseId")
	   @Query(value = "SELECT COUNT(*) FROM student_courses WHERE course_id = :courseId", nativeQuery = true)
	   Long countByCourseId(@Param("courseId") Long courseId);

	  // @Query("SELECT sc FROM StudentCourses sc WHERE sc.course.id = :courseId ORDER BY sc.studScore DESC")
	   @Query (value ="SELECT * FROM student_courses WHERE course_id=:courseId ORDER BY stud_score DESC", nativeQuery=true)
	   List<StudentCourses> findTopByCourseIdOrderByStudScoreDesc(Long courseId);

}
