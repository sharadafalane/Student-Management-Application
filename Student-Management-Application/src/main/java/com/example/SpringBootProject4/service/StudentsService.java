package com.example.SpringBootProject4.service;

import java.util.List;


import com.example.SpringBootProject4.model.Students;

public interface StudentsService
{
	public String upsert(Students stud);
	public Students getById(Long id);
	public List<Students> getAllStudents();
	public String deleteById(Long id);

	//public List<StudentCoursesDTO> getStudentCoursesDetails(Long studentId) ;

}
