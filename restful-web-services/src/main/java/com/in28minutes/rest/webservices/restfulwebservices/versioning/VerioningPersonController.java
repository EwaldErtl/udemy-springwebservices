package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerioningPersonController {


	@GetMapping( "/v1/person")
	public Person getPerson() { 
		return new Person("Ewald Ertl");
	}
	
	@GetMapping( "/v2/person")
	public PersonV2 getPersonV2() { 
		return new PersonV2(new Name("Ewald", "Ertl"));
	}
	
	@GetMapping( path = "/person", params = "version=1")
	public Person getPersonRequestParam() { 
		return new Person("Ewald Ertl");
	}
	
	@GetMapping( path = "/person", params = "version=2")
	public PersonV2 getPersonV2RequParam() { 
		return new PersonV2(new Name("Ewald", "Ertl"));
	}

	@GetMapping( path = "/person/header", headers = "X-API-VERSION=1")
	public Person getPersonRequestHeader() { 
		return new Person("Ewald Ertl");
	}
	
	@GetMapping( path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonV2RequestHeader() { 
		return new PersonV2(new Name("Ewald", "Ertl"));
	}

	@GetMapping( path = "/person/accept", produces =  "application/vnd.axxiome.app-v1+json")
	public Person getPersonAcceptParam() { 
		return new Person("Ewald Ertl");
	}
	
	@GetMapping( path = "/person/accept", params =  "application/vnd.axxiome.app-v2.json")
	public PersonV2 getPersonV2AcceptParam() { 
		return new PersonV2(new Name("Ewald", "Ertl"));
	}
}
