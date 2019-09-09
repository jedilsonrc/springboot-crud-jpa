package br.com.techjambo.springbootstudy.springbootcrudjpa.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import br.com.techjambo.springbootstudy.springbootcrudjpa.model.Post;
import br.com.techjambo.springbootstudy.springbootcrudjpa.model.User;
import br.com.techjambo.springbootstudy.springbootcrudjpa.repository.UserRepository;
import br.com.techjambo.springbootstudy.springbootcrudjpa.resource.exception.UserNotFoundException;

@RestController
public class UserJpaResource {

	//http://localhost:8080/h2-console/login.do
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable Integer id) {
		Optional<User>  user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException(String.format("id=%s", id));

		return user.get();
	}

	@GetMapping("/jpa/users-reference/{id}")
	public Resource<User> retrieveUserWithLink(@PathVariable Integer id) {
		Optional<User>  user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException(String.format("id=%s", id));

		Resource<User> resource = new Resource<User>(user.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> addUser(@Validated @RequestBody User user) {
		User userSaved = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public ResponseEntity<Object> removeUser(@PathVariable Integer id) {

		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException(String.format("id=%s", id));

		userRepository.delete(user.get());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveUsersPosts(@PathVariable Integer id) {
		Optional<User>  user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException(String.format("id=%s", id));

		return user.get().getUsersPosts();
	}
	
}
