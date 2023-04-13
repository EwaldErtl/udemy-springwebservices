package com.in28minutes.springboot.learnjpaandhibernate.course.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseJPARepository {

	@PersistenceContext
	private EntityManager entityManager;
	


	public void insert(Course course) { 
		this.entityManager.merge(course);
	}


	public Course findById(long id) { 
		return this.entityManager.find(Course.class, id);
	}

	public void deleteById(long id) { 
		Course coures = entityManager.find(Course.class, id); 
		this.entityManager.detach(coures);
	}
}
