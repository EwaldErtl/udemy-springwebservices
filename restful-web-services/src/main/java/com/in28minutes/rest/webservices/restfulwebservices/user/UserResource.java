package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService userDaoService;


	public UserResource(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}


	@GetMapping( "/users")
	public List<User> retrieveAllUsers() { 

		return userDaoService.findAll();

	}
	
	@GetMapping( "/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable("id") Integer userId) { 

		User user = userDaoService.findUser(userId);

		if ( user != null) {
			EntityModel<User> entityModel = EntityModel.of(user);

			WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

			entityModel.add(link.withRel("all-users"));
			return entityModel;
		} else { 
			throw new UserNotFoundException(String.format("user not found : %d", userId));
		}

	}

	@DeleteMapping( "/users/{id}")
	public User deleteUser(@PathVariable("id") Integer userId) { 

		User user = userDaoService.deleteUser(userId);

		if ( user != null) {
			return user;
		} else { 
			throw new UserNotFoundException(String.format("user not found : %d", userId));
		}

	}

	@PostMapping( "/users")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) { 
		User newUser = userDaoService.createUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).body(newUser);
	}
}
