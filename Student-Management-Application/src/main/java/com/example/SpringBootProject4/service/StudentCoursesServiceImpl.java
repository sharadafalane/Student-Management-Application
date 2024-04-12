package com.example.SpringBootProject4.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootProject4.model.Courses;
import com.example.SpringBootProject4.model.StudentCourses;
import com.example.SpringBootProject4.model.Students;


import com.example.SpringBootProject4.repository.CoursesRepository;
import com.example.SpringBootProject4.repository.StudentsRepository;
import com.example.SpringBootProject4.repository.StudentCoursesRepository;

@Service
public class StudentCoursesServiceImpl implements StudentCoursesService {

    @Autowired
    private StudentCoursesRepository studentCoursesRepository;
	@Autowired
    private StudentsRepository studentsRepository;
	@Autowired
    private CoursesRepository coursesRepository;

	@Override
    public String upsert(StudentCourses studentcourses)
    {
		if(studentcourses.getStudent()==null || studentcourses.getCourse() == null) {
    	        return "Error: Required data is missing.";
    	  }
    	  
    	 Students student = studentcourses.getStudent();
    	 Courses course = studentcourses.getCourse();
    	 
    	 if (student.getStudentId() == null || !studentsRepository.existsById(student.getStudentId())) {
    		 //studRepository.save(student);
    	      return "This student record does not exist in Students table";
    	  }

         if (course.getCourseId() == null || !coursesRepository.existsById(course.getCourseId())) {
    	       //coursesRepository.save(course);
    	       return "This course does not exist in Courses table";
    	  }
		 /*if(!studentCoursesRepository.existsBy(studentcourses.getId())){
		        studentCoursesRepository.save(studentcourses);
    	        return "Success";
    	    } else{
    	        return "Record already exists.";
    	    }*/
		   studentCoursesRepository.save(studentcourses);
	        return "Success";
    	}
    	
	   @Override
	    public StudentCourses getById(Long id) 
	    {
	        Optional<StudentCourses> findById = studentCoursesRepository.findById(id);
			if(findById.isPresent()) {
	        	return  findById.get();
	        }
	        return null;
	    }
		@Override
	    public List<StudentCourses> getAllStudentsCourses() 
	    {
	    	List<StudentCourses>studentcourses= (List<StudentCourses>) studentCoursesRepository.findAll();
	        return studentcourses ;
		}
	    
	   @Override
	    public String deleteById(Long id) 
	    {
	        if (studentCoursesRepository.existsById(id)) {
				studentCoursesRepository.deleteById(id);
	            return "Deleted Successfully";
	        }
	        return "No Record Found";
	    }
	    
     
}
