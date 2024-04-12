package com.example.SpringBootProject4;
import org.springframework.boot.SpringApplication;// This class is the main entry point for starting a Spring Boot application
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //marks the class as a Spring Boot application class
public class SpringBootProject4Application {

	public static void main(String[] args) //starting point for execution when we run the application.
	{
		SpringApplication.run(SpringBootProject4Application.class, args); 	//actually starts the Spring Boot application
	}

}
