package com.example.SpringBootProject4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
//No of students completed particular course with Highest Scorer
public class CourseCompletionStatsDTO {
	private String courseName;
	private Long studentsCompleted;
	private String highestScorerFirstName;
	private String highestScorerLastName;
	private Integer highestScore;

}
