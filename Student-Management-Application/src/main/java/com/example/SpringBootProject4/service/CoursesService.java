package com.example.SpringBootProject4.service;

import java.util.List;

import com.example.SpringBootProject4.dto.CourseCompletionStatsDTO;
import com.example.SpringBootProject4.model.Courses;

public interface CoursesService {
	   public String upsert(Courses course);
		public Courses getById(Long id);
		public List<Courses> getAllCourses();
		public String deleteById(Long id);
		List<CourseCompletionStatsDTO> getCourseCompletionStats();
}



