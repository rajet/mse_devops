package com.mse.devops.school;

import com.mse.devops.school.course.Course;
import com.mse.devops.school.course.CourseRepository;
import com.mse.devops.school.exams.Exam;
import com.mse.devops.school.exams.ExamRepository;
import com.mse.devops.school.student.Student;
import com.mse.devops.school.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolApplication {
	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(StudentRepository studentRepository,
									  CourseRepository courseRepository,
									  ExamRepository examRepository) {
		return args -> {
			// Create students
			Student emma = new Student();
			emma.setName("Emma Wilson");

			Student james = new Student();
			james.setName("James Smith");

			Student sophia = new Student();
			sophia.setName("Sophia Chen");

			// Save students
			studentRepository.save(emma);
			studentRepository.save(james);
			studentRepository.save(sophia);

			// Create courses
			Course programming = new Course();
			programming.setName("Introduction to Programming");

			Course dataStructures = new Course();
			dataStructures.setName("Data Structures");

			// Save courses
			courseRepository.save(programming);
			courseRepository.save(dataStructures);

			// Create exams
			Exam javaMidterm = new Exam();
			javaMidterm.setName("Java Midterm");
			javaMidterm.setCourse(programming);

			Exam algorithmsFinal = new Exam();
			algorithmsFinal.setName("Algorithms Final");
			algorithmsFinal.setCourse(dataStructures);

			// Save exams
			examRepository.save(javaMidterm);
			examRepository.save(algorithmsFinal);

			// Connect students with courses and exams
			emma.getCourses().add(programming);
			emma.getExams().add(javaMidterm);

			james.getCourses().add(programming);
			james.getCourses().add(dataStructures);
			james.getExams().add(algorithmsFinal);

			sophia.getCourses().add(dataStructures);
			sophia.getExams().add(javaMidterm);
			sophia.getExams().add(algorithmsFinal);

			// Save the updated students
			studentRepository.save(emma);
			studentRepository.save(james);
			studentRepository.save(sophia);

			System.out.println("Sample data has been initialized!");
		};
	}
}

