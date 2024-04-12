package com.example.SpringBootProject4.model;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "StudentCourses" ,uniqueConstraints = {@UniqueConstraint(columnNames = { "student_id", "course_id" })})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCourses {
	 
	@Id
   //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JsonBackReference
	@JsonIgnore
	@JoinColumn(name = "student_id")  //nullable=false
	private Students student;

	@ManyToOne //fetch=FetchType. 1.LAZY 2.EAGER 3.dEFAULT
	@JsonBackReference
	@JsonIgnore
	@JoinColumn(name = "course_id")
	private Courses course;

	@Column(name = "start_date")
	@NotNull
	private LocalDate startDate;

	@Column(name = "end_date")
	@NotNull
	private LocalDate endDate;
	
	@Column(name = "stud_score")
	@NotNull
	private Integer studScore;
	
	
	@PrePersist //Annotation in JPA and used to execute method before entity object inserted in database.
	@PreUpdate  //Annotation in JPA and used to execute method when entity object is updated in database.
	private void validateDateAndStuScore()
	{
		if(endDate.isBefore(startDate))
		{
			throw new IllegalStateException("EndDate should be greater than startDate");  
		}
		
		if(studScore>course.getMarksAllocated())
		{
			throw new IllegalStateException("Student Score should be less than or equals to marks allocated to course");
		}
	}
   @JsonIgnore
   public Students getStudent() 
	{
		return student;
	}
   @JsonProperty
   public void setStudent(Students student) 
	{
		this.student = student;
	}
   @JsonIgnore
   public Courses getCourse() 
	{
		return course;
	}
   @JsonProperty
   public void setCourse(Courses course)
	{
		this.course = course;
	}


}
