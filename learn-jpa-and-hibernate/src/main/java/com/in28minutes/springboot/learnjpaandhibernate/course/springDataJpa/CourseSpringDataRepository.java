package com.in28minutes.springboot.learnjpaandhibernate.course.springDataJpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
	
	List<Course> findByAuthor(String Author);
	List<Course> findByName(String name);
}
