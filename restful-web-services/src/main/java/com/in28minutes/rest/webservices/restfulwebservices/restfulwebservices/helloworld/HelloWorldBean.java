package com.in28minutes.rest.webservices.restfulwebservices.restfulwebservices.helloworld;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(final String msg) { 
		this.message = msg;
	}

	
	public HelloWorldBean() {
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}


}
