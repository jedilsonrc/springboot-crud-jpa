package br.com.techjambo.springbootstudy.springbootcrudjpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min=2,message="Name should have at least 2 characters")
	private String name;
	
	@Past
	private Date birthDay;

	@OneToMany(mappedBy="user")
	private List<Post> usersPosts;
	
	public User() {
		
	}
	
	public User(Integer id, String name, Date birthDay) {
		super();
		this.id = id;
		this.name = name;
		this.birthDay = birthDay;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public List<Post> getUsersPosts() {
		return usersPosts;
	}

	public void setUsersPosts(List<Post> usersPosts) {
		this.usersPosts = usersPosts;
	}
	
	
	
}
