package com.in28minutes.springboot.learnjpaandhibernate.course.jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

	private  CourseJdbcRepository courseJdbcRepository;


	
	public CourseJdbcCommandLineRunner(CourseJdbcRepository courseJdbcRepository) {
		this.courseJdbcRepository = courseJdbcRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		courseJdbcRepository.insert(new Course(1, "Learn AWS now", "in 28minutes"));
		courseJdbcRepository.insert(new Course(2, "Learn AWS2 now", "in 28minutes"));
		courseJdbcRepository.insert(new Course(3, "Learn AWS3 now", "in 28minutes"));

		courseJdbcRepository.deleteCourse(2);

		System.out.println(courseJdbcRepository.findById(1));
		System.out.println(courseJdbcRepository.findById(3));
	}
	
}
