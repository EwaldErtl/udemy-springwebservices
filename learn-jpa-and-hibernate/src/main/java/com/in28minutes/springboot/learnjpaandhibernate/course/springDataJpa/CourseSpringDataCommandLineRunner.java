package com.in28minutes.springboot.learnjpaandhibernate.course.springDataJpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;

@Component
public class CourseSpringDataCommandLineRunner implements CommandLineRunner {

	private  CourseSpringDataRepository courseSpringDataRepository;


	
	public CourseSpringDataCommandLineRunner(CourseSpringDataRepository courseSpringDataRepository) {
		this.courseSpringDataRepository = courseSpringDataRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		courseSpringDataRepository.save(new Course(10, "Learn AWS now", "in 28minutes"));
		courseSpringDataRepository.save(new Course(20, "Learn AWS2 now", "in 28minutes"));
		courseSpringDataRepository.save(new Course(30, "Learn AWS3 now", "in 28minutes"));

		courseSpringDataRepository.deleteById(20L);

		System.out.println(courseSpringDataRepository.findById(10L));
		System.out.println(courseSpringDataRepository.findById(30L));


		courseSpringDataRepository.findAll();

		System.out.println(courseSpringDataRepository.findByAuthor("in 28minutes"));
		System.out.println(courseSpringDataRepository.findByName("Learn AWS now"));
	}
	
}
