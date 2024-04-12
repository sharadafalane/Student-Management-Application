package com.example.SpringBootProject4.service;

import java.util.List;

import com.example.SpringBootProject4.model.StudentCourses;

public interface StudentCoursesService {
	    public String upsert(StudentCourses studentCourses);
		public StudentCourses getById(Long id);
		public List<StudentCourses> getAllStudentsCourses();
		public String deleteById(Long id);

		//public boolean hasStudentCompletedCourse(Long studentId, Long courseId);
}
