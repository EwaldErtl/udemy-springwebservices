package com.in28minutes.springboot.learnspringboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@GetMapping()
	public List<Course> retrieveAllCourses() {
		return Arrays.asList(new Course(1, "Learn AWS", "in28minutes"),
			new Course(2, "Learn DevOps", "in28minutes"),
			new Course(2, "Learn azue", "in28minutes"));
	}
	
}
