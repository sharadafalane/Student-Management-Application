package com.example.SpringBootProject4.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//@Table(name = "student")
public class Students 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;
 
    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    @Email(message = "Please provide a valid email address")
    private String email;
 
    @Column(name = "contact_no",unique=true)
    private String contact;
    
    @OneToMany(mappedBy = "student",cascade = CascadeType.REMOVE)  //Maintains referential integrity
    @JsonManagedReference
    @JsonIgnore
    private List<StudentCourses> coursesCompleted;
    
    @OneToMany(mappedBy="student")
    @JsonManagedReference
    @JsonIgnore
    private List<FeesDetails>feesdetails;

}










