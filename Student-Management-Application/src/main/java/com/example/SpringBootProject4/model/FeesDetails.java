package com.example.SpringBootProject4.model;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "FeesDetails" ,uniqueConstraints = {@UniqueConstraint(columnNames = { "student_id", "course_id" })})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeesDetails 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@ManyToOne
    @JoinColumn(name = "student_id")
	@JsonBackReference
	@JsonIgnore
    private Students student;

    @ManyToOne
    @JoinColumn(name = "course_id")  //nullable=false
	@JsonBackReference
	@JsonIgnore
    private Courses course;

    @Column(name="Fees")
    @NotNull
    private BigDecimal amount;

    @Column(name="paid_status")
    @NotNull
    private boolean paid;

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
