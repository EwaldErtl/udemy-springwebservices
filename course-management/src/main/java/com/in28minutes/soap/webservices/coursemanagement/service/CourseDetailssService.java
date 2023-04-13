package com.in28minutes.soap.webservices.coursemanagement.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.in28minutes.soap.webservices.coursemanagement.Course;

@Component
public class CourseDetailssService {

	private static List<Course>  courses = new ArrayList<>();

	static { 
		Course course = new Course(1,"Spring", "10 Steps");
		courses.add(course);

		course = new Course(2, "Spring MVC", "10 Examples");
		courses.add(course);

		course = new Course(3, "Spring Boot", "6k Students");
		courses.add(course);

		course = new Course(4, "Maven", "Most popular maven course");
		courses.add(course);
	}


	public Course findById(int id)  {
		for ( Course course : courses) { 
			if (course.getId() == id ) { 
				return course;
			}
		}
		return null;
	}


	public List<Course> findAll() { 
		return courses;
	}

	public Optional<Course> deleteById(int id) {
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();

			if (course.getId() == id ) { 
				iterator.remove();
				return Optional.of(course);
			}
		}
		return Optional.empty();

	}


}
