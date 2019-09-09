package br.com.techjambo.springbootstudy.springbootcrudjpa.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.techjambo.springbootstudy.springbootcrudjpa.dao.UserDao;
import br.com.techjambo.springbootstudy.springbootcrudjpa.model.User;
import br.com.techjambo.springbootstudy.springbootcrudjpa.resource.exception.UserNotFoundException;

@RestController
public class UserResource {

	@Autowired
	private UserDao userDao;

	@GetMapping("users/{id}")
	public User retrieveUser(@PathVariable Integer id) {
		User user = userDao.findById(id);

		if (user == null)
			throw new UserNotFoundException(String.format("id=%s", id));

		return user;
	}

	@GetMapping("users-reference/{id}")
	public Resource<User> retrieveUserWithLink(@PathVariable Integer id) {
		User user = userDao.findById(id);

		if (user == null)
			throw new UserNotFoundException(String.format("id=%s", id));

		Resource<User> resource = new Resource<User>(user);

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDao.findAll();
	}

	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Validated @RequestBody User user) {
		User userSaved = userDao.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> removeUser(@PathVariable Integer id) {

		User user = userDao.findById(id);

		if (user == null)
			throw new UserNotFoundException(String.format("id=%s", id));

		userDao.delete(id);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

		return ResponseEntity.created(location).build();
	}

}
