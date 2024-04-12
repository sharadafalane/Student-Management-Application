package com.example.SpringBootProject4.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.SpringBootProject4.model.Courses;
import com.example.SpringBootProject4.dto.CourseCompletionStatsDTO;
import com.example.SpringBootProject4.service.CoursesServiceImpl;


@RestController
public class CoursesRestController {

    @Autowired
    private CoursesServiceImpl courseService;

    @PostMapping(value = "${endpoint.courses.create}")
    public ResponseEntity<String> createCourse(@RequestBody Courses course)
    {
        String status = courseService.upsert(course);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping(value = "${endpoint.courses.getOne}")
    public ResponseEntity<Courses> getCourse(@PathVariable Long id) 
    {
        Courses course = courseService.getById(id);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
    }
    @GetMapping(value = "${endpoint.courses.getAll}")
    public ResponseEntity<List<Courses>> getAllCourses() 
    {
        List<Courses> allCourses = courseService.getAllCourses();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }
    @PutMapping(value = "${endpoint.courses.update}")
    public ResponseEntity<String> updateCourse(@RequestBody Courses course) 
    {
        String status = courseService.upsert(course);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping(value = "${endpoint.courses.delete}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id)
    {
        String status = courseService.deleteById(id);
        if (status.equals("No Record Found")) {
            return new ResponseEntity<>("No Record Found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
    }


    //No of students completed particular course with highest scorer
    @GetMapping("/course-completion-stats")
    public ResponseEntity<List<CourseCompletionStatsDTO>> getCourseCompletionStats() 
    {
        List<CourseCompletionStatsDTO> completionStatsList =courseService.getCourseCompletionStats();

        if (completionStatsList.isEmpty()) 
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(completionStatsList, HttpStatus.OK);
    }

}
