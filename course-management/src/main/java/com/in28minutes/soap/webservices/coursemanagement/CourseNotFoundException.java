package com.in28minutes.soap.webservices.coursemanagement;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT, 
	customFaultCode = "{http://in28minutes.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String message) {
		super(message);
	}

	
}
