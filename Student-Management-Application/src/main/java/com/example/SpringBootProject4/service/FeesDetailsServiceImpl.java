package com.example.SpringBootProject4.service;

import com.example.SpringBootProject4.repository.FeesDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootProject4.model.Courses;
import com.example.SpringBootProject4.model.FeesDetails;
import com.example.SpringBootProject4.model.Students;
import com.example.SpringBootProject4.repository.CoursesRepository;
import com.example.SpringBootProject4.repository.StudentsRepository;

import java.util.List;
@Service
public class FeesDetailsServiceImpl implements FeesDetailsService
{
    @Autowired
    private FeesDetailsRepository feesDetailsRepository;
    @Autowired
    private StudentsRepository studentRepository;
    @Autowired
    private CoursesRepository courseRepository;
    @Override
    public String upsert(FeesDetails fee) 
    {
        if (fee.getStudent() == null || fee.getCourse() == null)
        {
            return "Error: Required data is missing.";
        }
        Students student = fee.getStudent();
        Courses course = fee.getCourse();

        if (!studentRepository.existsById(student.getStudentId()))
        {
            return "Error: This student record does not exist in Students table";
        }

        if (!courseRepository.existsById(course.getCourseId())) 
        {
            return "Error: This course does not exist in Courses table";
        }
        feesDetailsRepository.save(fee);
        return "Success: Fees details saved successfully.";
    }

    @Override
    public List<FeesDetails> getById(Long studentId,Long courseId)
    {
       List<FeesDetails> feesdetails;

       if (courseId != null) {
            feesdetails = feesDetailsRepository.findByStudentStudentIdAndCourseCourseId(studentId, courseId);
        } else {
            feesdetails = feesDetailsRepository.findByStudentStudentId(studentId);
        }
        return feesdetails;
    }

    @Override
    public List<FeesDetails> getAllFeesDetails() 
    {
        return feesDetailsRepository.findAll();
    }

    @Override
    public String deleteById(Long id) 
    {
        try{
            feesDetailsRepository.deleteById(id);
            return "Success: Fees details deleted successfully.";
        } catch (Exception e){
            return "Error: " + e.getMessage(); //EmptyResultDataAccessException if record not found with given id
        }
    }

}
