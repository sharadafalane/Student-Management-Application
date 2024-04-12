package com.example.SpringBootProject4.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.SpringBootProject4.model.Students;
import com.example.SpringBootProject4.dto.StudentCoursesDTO;
import com.example.SpringBootProject4.service.StudentsServiceImpl;

@RestController
public class StudentsRestController
{
    @Autowired
    private StudentsServiceImpl studService;
    @PostMapping(value = "${endpoint.student.create}")    //consumes = "application/json", produces = "application/json"
    public ResponseEntity<String> createStudent(@RequestBody Students stud) 
    {
        String status = studService.upsert(stud);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }
    @GetMapping(value = "${endpoint.student.getOne}")
    public ResponseEntity<Students> getStudent(@PathVariable Long id)
    {
        Students s = studService.getById(id);
        if (s == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
    }

    @GetMapping(value = "${endpoint.student.getAll}")
    public ResponseEntity<List<Students>> getAllStudents() 
    {
        List<Students> allStudents = studService.getAllStudents();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @PutMapping(value = "${endpoint.student.update}")
    public ResponseEntity<String> updateStudent(@RequestBody Students stud) 
    {
        String status = studService.upsert(stud);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping(value = "${endpoint.student.delete}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) 
    {
        String status = studService.deleteById(id);
        if (status.equals("No Record Found"))
        {
            return new ResponseEntity<>("No Record Found", HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
    }
    
   //gives the information of courses completed by particular student
    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<StudentCoursesDTO>> getStudentCoursesDetails(@PathVariable Long studentId) 
    {
      
        List<StudentCoursesDTO> studentCourses = studService.getStudentCoursesDetails(studentId);
        if (studentCourses.isEmpty()) 
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

       return new ResponseEntity<>(studentCourses, HttpStatus.OK);
    }
    
   
}
