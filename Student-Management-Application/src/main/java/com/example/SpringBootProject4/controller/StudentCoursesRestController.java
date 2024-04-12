package com.example.SpringBootProject4.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBootProject4.model.StudentCourses;
import com.example.SpringBootProject4.service.StudentCoursesServiceImpl;

@RestController
public class StudentCoursesRestController {

    @Autowired
    private StudentCoursesServiceImpl studentCoursesService;

    @PostMapping(value = "${endpoint.studentCourses.create}")
    public ResponseEntity<String> createStudentCourse(@RequestBody StudentCourses studentCourses) 
    {
        String status = studentCoursesService.upsert(studentCourses);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }


    @GetMapping(value = "${endpoint.studentCourses.getOne}")
    public ResponseEntity<StudentCourses> getStudentCourse(@PathVariable Long id)
    {
        StudentCourses studentCourses = studentCoursesService.getById(id);
        if (studentCourses == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(studentCourses, HttpStatus.OK);
        }
    }

    @GetMapping(value = "${endpoint.studentCourses.getAll}")
    public ResponseEntity<List<StudentCourses>> getAllStudentCourses() 
    {
        List<StudentCourses> allStudentCourses = studentCoursesService.getAllStudentsCourses();
        return new ResponseEntity<>(allStudentCourses, HttpStatus.OK);
    }

    @PutMapping(value = "${endpoint.studentCourses.update}")
    public ResponseEntity<String> updateStudentCourse(@RequestBody StudentCourses studentCourses)
    {
        String status = studentCoursesService.upsert(studentCourses);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    //@RequestMapping(value = "${endpoint.studentCourses.delete}", method = RequestMethod.DELETE)
    @DeleteMapping(value = "${endpoint.studentCourses.delete}")
    public ResponseEntity<String> deleteStudentCourse(@PathVariable Long id)
    {
        String status = studentCoursesService.deleteById(id);
        if (status.equals("No Record Found")) {
            return new ResponseEntity<>("No Record Found", HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
    }
}
