package com.example.SpringBootProject4.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SpringBootProject4.model.Students;
import com.example.SpringBootProject4.dto.StudentCoursesDTO;
import com.example.SpringBootProject4.repository.StudentsRepository;
import com.example.SpringBootProject4.repository.StudentCoursesRepository;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired	
	private StudentsRepository studRepo;
	@Autowired
    private StudentCoursesRepository studentCoursesRepository;
	@Override
	public String upsert(Students stud)
	{
	    studRepo.save(stud);                              
		return "Succees";
	}
	@Override
	public Students getById(Long id) {
		
		Optional<Students>findById= studRepo.findById(id);
		if(findById.isPresent())
		{
			return findById.get();
		}
		return null;
	}

    @Override
	public List<Students> getAllStudents()
	{
		List<Students>students= studRepo.findAll();
		return students;
	}
    
    @Override
	public String deleteById(Long id)
	{
		if(studRepo.existsById(id))
		{
			studRepo.deleteById(id);
			return "Deleted Successfully";
		}
		return "No Record Found";
	}
    
    public List<StudentCoursesDTO> getStudentCoursesDetails(Long studentId) 
    {
        return studentCoursesRepository.getStudentCoursesDetails(studentId);
    }
    
}

