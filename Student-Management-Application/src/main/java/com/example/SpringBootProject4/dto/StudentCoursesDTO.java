package com.example.SpringBootProject4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Gives the information of courses completed by particular Student
public class StudentCoursesDTO {

    private Long studentId;
    private String fullName;
    private String lastName;
    private String courseName;
    private Integer studScore;
}