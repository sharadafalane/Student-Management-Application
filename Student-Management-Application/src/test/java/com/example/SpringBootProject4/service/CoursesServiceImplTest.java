package com.example.SpringBootProject4.service;

import com.example.SpringBootProject4.controller.CoursesRestController;
import com.example.SpringBootProject4.dto.CourseCompletionStatsDTO;
import com.example.SpringBootProject4.model.Courses;
import com.example.SpringBootProject4.model.StudentCourses;
import com.example.SpringBootProject4.model.Students;
import com.example.SpringBootProject4.repository.CoursesRepository;
import com.example.SpringBootProject4.repository.StudentCoursesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoursesServiceImplTest {

    @Mock
    private CoursesRepository coursesRepository;

    @Mock
    private StudentCoursesRepository studentCoursesRepository;

    @Mock
    private CoursesRestController courseRestController;
    @InjectMocks
    private CoursesServiceImpl coursesService;


    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void UpsertTest()
    {
        Courses course = new Courses();
        course.setCourseId(1L);
        course.setCourseName("Data Science");
        course.setMarksAllocated(200);
        when(coursesRepository.save(course)).thenReturn(course);
        String result = coursesService.upsert(course);
        assertEquals("Success", result);

    }
    @Test
    void getByIdTest_WithValidId()
    {
        long courseId=1L;
        Courses course=new Courses();
        course.setCourseId(courseId);
        course.setCourseName("Data Science");
        course.setMarksAllocated(200);
        Optional<Courses> optionalCourse = Optional.of(course);
        when(coursesRepository.findById(courseId)).thenReturn(optionalCourse);
        Courses result = coursesService.getById(courseId);
        assertEquals(course, result);
    }

    @Test
    void getByIdTest_WithInValidId()
    {
        Long courseId = 1L;
        Optional<Courses> optionalCourse = Optional.empty();
        when(coursesRepository.findById(courseId)).thenReturn(optionalCourse);
        Courses result = coursesService.getById(courseId);
        assertEquals(null, result);
    }

    @Test
    void GetAllCoursesTest()
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

        when(coursesRepository.findAll()).thenReturn(coursesList);
        List<Courses> result = coursesService.getAllCourses();
        assertEquals(coursesList, result);

    }

    @Test
    void DeleteByIdTest_WithExistingId() {

        long courseId=1L;
        Courses course1=new Courses();
        course1.setCourseId(courseId);
        course1.setCourseName("Cyber Security");
        course1.setMarksAllocated(100);
        when(coursesRepository.existsById(courseId)).thenReturn(true);
        String result = coursesService.deleteById(courseId);
        assertEquals("Delete Successfully", result);

    }

    @Test
    void DeleteByIdTest_WithNonExistingId()
    {
        Long id = 1L;
        when(coursesRepository.existsById(id)).thenReturn(false);
        String result = coursesService.deleteById(id);
        assertEquals("No Record Found", result);

    }

    @Test
    void getCourseCompletionStats_ReturnsStatsList()
    {
        List<Courses> courses = new ArrayList<>();
        Courses course1= new Courses();
        course1.setCourseId(1L);
        course1.setCourseName("Cloud Computing");
        courses.add(course1);

        List<StudentCourses> studentCoursesList = new ArrayList<>();
        StudentCourses studentCourse1 = new StudentCourses();
        studentCourse1.setId(1L);
        studentCourse1.setCourse(course1);
        studentCourse1.setStudScore(90);
        Students student1 = new Students();
        student1.setFirstName("Juhi");
        student1.setLastName("Deshmukh");
        studentCourse1.setStudent(student1);
        studentCoursesList.add(studentCourse1);

        when(coursesRepository.findAll()).thenReturn(courses);
        when(studentCoursesRepository.countByCourseId(1L)).thenReturn(1L);
        when(studentCoursesRepository.findTopByCourseIdOrderByStudScoreDesc(1L)).thenReturn(studentCoursesList);
        List<CourseCompletionStatsDTO> result = coursesService.getCourseCompletionStats();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Cloud Computing", result.get(0).getCourseName());
        assertEquals("Juhi", result.get(0).getHighestScorerFirstName());
        assertEquals("Deshmukh", result.get(0).getHighestScorerLastName());
        assertEquals(90, result.get(0).getHighestScore());
        assertEquals(1L, result.get(0).getStudentsCompleted());
    }



   @Test
    void getCourseCompletionStats_NoStatsAvailable()
   {

        CoursesRepository coursesRepository = mock(CoursesRepository.class);
        StudentCoursesRepository studentCoursesRepository = mock(StudentCoursesRepository.class);
        when(coursesRepository.findAll()).thenReturn(new ArrayList<>());
        List<CourseCompletionStatsDTO> result = coursesService.getCourseCompletionStats();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
