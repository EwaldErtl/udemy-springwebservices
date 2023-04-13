package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.in28minutes.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	private UserRepository userRepository;

	private PostRepository postRepository;


	public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}


	@GetMapping( "/jpa/users")
	public List<User> retrieveAllUsers() { 

		return userRepository.findAll();

	}
	
	@GetMapping( "/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable("id") Integer userId) { 

		Optional<User> user = userRepository.findById(userId);

		if ( user.isPresent()) {
			EntityModel<User> entityModel = EntityModel.of(user.get());

			WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

			entityModel.add(link.withRel("all-users"));
			return entityModel;
		} else { 
			throw new UserNotFoundException(String.format("user not found : %d", userId));
		}

	}

	@DeleteMapping( "/jpa/users/{id}")
	public User deleteUser(@PathVariable("id") Integer userId) { 
		Optional<User> user = userRepository.findById(userId);
		userRepository.deleteById(userId);

		if ( user.isPresent()) {
			return user.get();
		} else { 
			throw new UserNotFoundException(String.format("user not found : %d", userId));
		}

	}

	@GetMapping( "/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable("id") Integer userId) { 
		Optional<User> user = userRepository.findById(userId);

		if ( user.isPresent()) {
			return user.get().getPosts();
		} else { 
			throw new UserNotFoundException(String.format("user not found : %d", userId));
		}

	}

	@PostMapping( "/jpa/users/{id}/posts")
	public ResponseEntity<Object> retrievePostsForUser(@PathVariable("id") Integer userId, @Valid @RequestBody Post post) { 
		Optional<User> user = userRepository.findById(userId);

		if ( user.isPresent()) {
			post.setUser(user.get());
			Post save = postRepository.save(post);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(user.get().getId()).toUri();

			return ResponseEntity.created(uri).build();
		} else { 
			throw new UserNotFoundException(String.format("user not found : %d", userId));
		}

	}

	@PostMapping( "/jpa/users")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) { 
		User newUser = userRepository.saveAndFlush(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).body(newUser);
	}
}
