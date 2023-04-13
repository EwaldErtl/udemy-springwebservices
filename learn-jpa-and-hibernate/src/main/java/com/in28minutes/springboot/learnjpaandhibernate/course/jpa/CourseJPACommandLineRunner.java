package com.in28minutes.springboot.learnjpaandhibernate.course.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import com.in28minutes.springboot.learnjpaandhibernate.course.jdbc.CourseJdbcRepository;

@Component
public class CourseJPACommandLineRunner implements CommandLineRunner {

	private  CourseJPARepository courseJPARepository;


	
	public CourseJPACommandLineRunner(CourseJPARepository courseJPARepository) {
		this.courseJPARepository = courseJPARepository;
	}



	@Override
	public void run(String... args) throws Exception {
		courseJPARepository.insert(new Course(5, "Learn AWS now", "in 28minutes"));
		courseJPARepository.insert(new Course(6, "Learn AWS2 now", "in 28minutes"));
		courseJPARepository.insert(new Course(7, "Learn AWS3 now", "in 28minutes"));

		courseJPARepository.deleteById(6);

		System.out.println(courseJPARepository.findById(5));
		System.out.println(courseJPARepository.findById(7));
	}
	
}
