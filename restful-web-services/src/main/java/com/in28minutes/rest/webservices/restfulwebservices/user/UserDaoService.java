package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User>  users = new ArrayList<>();

	private static int usersCount=3;


	static { 
		users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(2, "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(3, "Jim", LocalDate.now().minusYears(20)));
	}



	public List<User> findAll() { 
		return users;
	}


	public User findUser(final Integer id) {
		return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
	}


	public User createUser(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}


	public User deleteUser(Integer userId) {

		User user = findUser(userId);
		if (user != null) { 
			users.removeIf(userRemove -> userRemove.getId().equals(userId));
		}
		return user;
	}


}
