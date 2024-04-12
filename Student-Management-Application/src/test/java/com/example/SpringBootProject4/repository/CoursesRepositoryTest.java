package com.example.SpringBootProject4.repository;

import com.example.SpringBootProject4.model.Courses;

import com.example.SpringBootProject4.repository.CoursesRepository;
import com.example.SpringBootProject4.service.CoursesServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CoursesRepositoryTest {

    @Mock
    private CoursesRepository coursesRepositoryMock;

    @InjectMocks
    private CoursesServiceImpl coursesService;

    public CoursesRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveCourseTest() {

        Courses course = new Courses();
        course.setCourseId(1L);
        course.setCourseName("Data Science");
        course.setMarksAllocated(100);
        when(coursesRepositoryMock.save(course)).thenReturn(course);
        String result = coursesService.upsert(course);
        assertThat(result).isEqualTo("Success");
    }

    @Test
    public void findCourseByIdTest() {

        Courses course = new Courses();
        course.setCourseId(1L);
        course.setCourseName("Data Science");
        course.setMarksAllocated(100);
        coursesRepositoryMock.save(course);
        when(coursesRepositoryMock.findById(1L)).thenReturn(Optional.of(course));

        Courses result = coursesService.getById(1L);
        assertThat(result).isEqualTo(course);
    }

    @Test
    public void findAllCoursesTest() {
        // Given
        List<Courses> coursesList = new ArrayList<>();
        Courses course1 = new Courses();
        course1.setCourseId(1L);
        course1.setCourseName("Data Science");
        course1.setMarksAllocated(100);

        Courses course2 = new Courses();
        course2.setCourseId(2L);
        course2.setCourseName("Machine Learning");
        course2.setMarksAllocated(100);

        coursesRepositoryMock.save(course1);
        coursesRepositoryMock.save(course2);
        when(coursesRepositoryMock.findAll()).thenReturn(coursesList);

        List<Courses> result = coursesService.getAllCourses();

        assertThat(result).isEqualTo(coursesList);
    }

    @Test
    public void deleteCourseByIdTest()
    {
        Courses course1 = new Courses();
        course1.setCourseId(1L);
        course1.setCourseName("Data Science");
        course1.setMarksAllocated(100);
        coursesRepositoryMock.save(course1);
        when(coursesRepositoryMock.existsById(1L)).thenReturn(true);
        String result = coursesService.deleteById(1L);
        assertThat(result).isEqualTo("Delete Successfully");
    }
}
