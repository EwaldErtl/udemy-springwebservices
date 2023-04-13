package com.in28minutes.soap.webservices.coursemanagement;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.soap.webservices.coursemanagement.service.CourseDetailssService;
import com.in28minutes.soap.webservices.coursemanagement.wsdl.CourseDetails;
import com.in28minutes.soap.webservices.coursemanagement.wsdl.DeleteCourseDetailsRequest;
import com.in28minutes.soap.webservices.coursemanagement.wsdl.DeleteCourseDetailsResponse;
import com.in28minutes.soap.webservices.coursemanagement.wsdl.GetAllCourseDetailsRequest;
import com.in28minutes.soap.webservices.coursemanagement.wsdl.GetAllCourseDetailsResponse;
import com.in28minutes.soap.webservices.coursemanagement.wsdl.GetCourseDetailsRequest;
import com.in28minutes.soap.webservices.coursemanagement.wsdl.GetCourseDetailsResponse;
import com.in28minutes.soap.webservices.coursemanagement.wsdl.Status;

@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailssService service;
	
	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse getCourse(@RequestPayload GetCourseDetailsRequest request) {

		Course findById = service.findById(request.getId().intValue());


		if (findById == null) { 
			throw new CourseNotFoundException("Invalid course ID:" + request.getId());
		}
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(findById));
		return response;
	}

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourse(@RequestPayload DeleteCourseDetailsRequest request) {

		Optional<Course> findById = service.deleteById(request.getId().intValue());


		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		if (findById.isPresent()) {
			response.setStatus(Status.SUCCESS);
		}  else {
			response.setStatus(Status.ERROR);
		}
		return response;
	}

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse getAllCourses(@RequestPayload GetAllCourseDetailsRequest request) {

		List<Course> findAll = service.findAll();



		return mapGetAllCourseDetailsResponse(findAll);
	}

	private GetAllCourseDetailsResponse mapGetAllCourseDetailsResponse(List<Course> findAll) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();

		List<CourseDetails> details = findAll.stream().map(CourseDetailsEndpoint::mapCourse).toList();
		response.getCourseDetails().addAll(details);
		return response;
	}

	public static CourseDetails mapCourse(Course course) {
		CourseDetails details = new CourseDetails();

		details.setId(BigInteger.valueOf(course.getId()));
		details.setName(course.getName());
		details.setDescription(course.getDescription());

		return details;
	}
}
