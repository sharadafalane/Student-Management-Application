package com.example.SpringBootProject4.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SpringBootProject4.model.Courses;
import com.example.SpringBootProject4.model.StudentCourses;
import com.example.SpringBootProject4.dto.CourseCompletionStatsDTO;
import com.example.SpringBootProject4.repository.CoursesRepository;
import com.example.SpringBootProject4.repository.StudentCoursesRepository;
@Service
public class CoursesServiceImpl implements CoursesService {

	@Autowired
    private  CoursesRepository coursesRepository;
	
	@Autowired 
	private StudentCoursesRepository studentCoursesRepository;

    @Override
    public String upsert(Courses course)
    {
       coursesRepository.save(course);
        return "Success";
    }

    @Override
    public Courses getById(Long id) 
    {
        Optional<Courses> optionalCourses = coursesRepository.findById(id);
        if(optionalCourses.isPresent()) {
        	return optionalCourses.get();
        }
        return null;
      
    }

    @Override
    public List<Courses> getAllCourses() 
    {
        List<Courses> courses = coursesRepository.findAll();
        return courses;
    }


    @Override
    public String deleteById(Long id)
    {
        if(coursesRepository.existsById(id)) {
            coursesRepository.deleteById(id);
            return "Delete Successfully";
        }
        return "No Record Found";
    }
    @Override
    public List<CourseCompletionStatsDTO> getCourseCompletionStats() 
    {
    	HashMap<String, CourseCompletionStatsDTO> completionStatsMap = new HashMap<>();
        List<Courses> courses = coursesRepository.findAll(); 
    	   
        for (Courses course : courses) 
        {
            Long studentsCompleted = studentCoursesRepository.countByCourseId(course.getCourseId());
            List<StudentCourses> highestScorers = studentCoursesRepository.findTopByCourseIdOrderByStudScoreDesc(course.getCourseId());

    	        if (!highestScorers.isEmpty())
    	        {
    	            StudentCourses highestScorerCourse = highestScorers.get(0);
                    String highestScorerFirstName = highestScorerCourse.getStudent().getFirstName();
    	            String highestScorerLastName = highestScorerCourse.getStudent().getLastName();
    	            Integer highestScore = highestScorerCourse.getStudScore();

                    CourseCompletionStatsDTO statsDTO = completionStatsMap.get(course.getCourseName());
    	            if (statsDTO == null)
                    {
                        statsDTO = new CourseCompletionStatsDTO();
    	                statsDTO.setCourseName(course.getCourseName());
    	                statsDTO.setStudentsCompleted(studentsCompleted);
    	                statsDTO.setHighestScorerFirstName(highestScorerFirstName);
    	                statsDTO.setHighestScorerLastName(highestScorerLastName);
    	                statsDTO.setHighestScore(highestScore);
                        completionStatsMap.put(course.getCourseName(), statsDTO);
    	            } else {
    	                
    	                statsDTO.setStudentsCompleted(studentsCompleted);
    	                statsDTO.setHighestScorerFirstName(highestScorerFirstName);
    	                statsDTO.setHighestScorerLastName(highestScorerLastName);
    	                statsDTO.setHighestScore(highestScore);
    	            }
    	        }
    	    }
         return new ArrayList<>(completionStatsMap.values());
    	}

    }




