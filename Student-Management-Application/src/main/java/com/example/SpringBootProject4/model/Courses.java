package com.example.SpringBootProject4.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//@Table(name = "course")
public class Courses {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //1.SEQUENCE 2.AUTO 3.TABLE 4.UUID (UNIVERSLY UNIQUE IDENTIFIER)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name", unique = true)
    @NotNull
    private String courseName;

    @Column(name = "Marks_Allocated")
    private Integer marksAllocated;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference //TO MANAGE BIDIRECTIONAL RELATIONSHIP
    @JsonIgnore //TO AVOID ERROR UNSUPPORTED MEDIA TYPE
    private List<StudentCourses> studentCourses;
    
    @OneToMany(mappedBy="course")
    @JsonManagedReference
    @JsonIgnore
    private List<FeesDetails>feesdetails;

    
    
 }