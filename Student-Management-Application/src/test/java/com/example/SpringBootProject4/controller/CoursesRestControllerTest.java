package com.example.SpringBootProject4.controller;

import com.example.SpringBootProject4.dto.CourseCompletionStatsDTO;
import com.example.SpringBootProject4.model.Courses;
import com.example.SpringBootProject4.service.CoursesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


public class CoursesRestControllerTest{


   /* private CoursesServiceImpl courseServiceMock;   //can be use if coursesRestController has constructor injection
    private CoursesRestController coursesRestController;

    @BeforeEach
    void setUp() {
        courseServiceMock = mock(CoursesServiceImpl.class);
        coursesRestController = new CoursesRestController(courseServiceMock);
    }*/
    @Mock
    private CoursesServiceImpl courseService; //mocking

    @InjectMocks
    private CoursesRestController coursesRestController;

    @BeforeEach
    void init()
    {

        MockitoAnnotations.initMocks(this);
    }
    @Test
    void createCourseTest()
    {
        Courses course=new Courses();
        course.setCourseId(1L);
        course.setCourseName("Data Science");
        course.setMarksAllocated(200);

        when(courseService.upsert((course))).thenReturn("Success"); //stubbing behaviour of mock
        ResponseEntity<String> response=coursesRestController.createCourse(course);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals("Success",response.getBody());

    }

    @Test
    void getCourseTest_withValidId() {
        long courseId = 1L;
        Courses course = new Courses();
        course.setCourseId(courseId);
        course.setCourseName("Data Science");
        course.setMarksAllocated(200);
        when(courseService.getById(courseId)).thenReturn(course);
        ResponseEntity<Courses> response = coursesRestController.getCourse(courseId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(course, response.getBody());
    }

    @Test
    void getCourseTest_courseNotFound()
    {
        long courseId = 1L;
        when(courseService.getById(courseId)).thenReturn(null);
        ResponseEntity<Courses> response = coursesRestController.getCourse(courseId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllCourses()
    {

        List<Courses> coursesList = new ArrayList<>();
        Courses course1=new Courses();
        course1.setCourseId(1L);
        course1.setCourseName("Cyber Security");
        course1.setMarksAllocated(100);

        Courses course2=new Courses();
        course2.setCourseId(2L);
        course2.setCourseName("Computer Networks");
        course2.setMarksAllocated(200);

        coursesList.add(course1);
        coursesList.add(course2);
        when(courseService.getAllCourses()).thenReturn(coursesList);
        ResponseEntity<List<Courses>> response = coursesRestController.getAllCourses();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(coursesList, response.getBody());

    }

    @Test
    void UpdateCourseTest()
    {
        Courses course=new Courses();
        course.setCourseId(1L);
        course.setCourseName("Data Science");
        course.setMarksAllocated(200);

        when(courseService.upsert((course))).thenReturn("Success");
        ResponseEntity<String> response=coursesRestController.createCourse(course);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals("Success",response.getBody());
    }

    @Test
    void deleteCourseTest_WithValidId()
    {
        Long courseId = 1L;
       /* Courses course=new Courses();
        course.setCourseId(courseId);
        course.setCourseName("Data Science");
        course.setMarksAllocated(200);*/
        when(courseService.deleteById(courseId)).thenReturn("Delete Successfully");
        ResponseEntity<String> response = coursesRestController.deleteCourse(courseId);
        verify(courseService).deleteById(courseId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Delete Successfully", response.getBody());
    }

    @Test
    void  deleteCourseTest_WithInvalidId()
    {
        Long courseId = 5L;
        when(courseService.deleteById(courseId)).thenReturn("No Record Found");
        ResponseEntity<String> response = coursesRestController.deleteCourse(courseId);
        verify(courseService).deleteById(courseId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getCourseCompletionStats_whenStatsAvailable()
    {
        List<CourseCompletionStatsDTO> statsList = new ArrayList<>();
        CourseCompletionStatsDTO D1=new CourseCompletionStatsDTO();
        D1.setCourseName("Operating System");
        D1.setStudentsCompleted(2L);
        D1.setHighestScorerFirstName("Juhi");
        D1.setHighestScorerLastName("Deshmukh");
        D1.setHighestScore(150);
        statsList.add(D1);
        when(courseService.getCourseCompletionStats()).thenReturn(statsList);
        ResponseEntity<List<CourseCompletionStatsDTO>> response = coursesRestController.getCourseCompletionStats();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(statsList, response.getBody());

    }

    @Test
    void getCourseCompletionStats_whenStatsNotAvailable()
    {
        List<CourseCompletionStatsDTO> statsList = new ArrayList<>();
        when(courseService.getCourseCompletionStats()).thenReturn(statsList);
        ResponseEntity<List<CourseCompletionStatsDTO>> response = coursesRestController.getCourseCompletionStats();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
