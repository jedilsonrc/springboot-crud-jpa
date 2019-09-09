package br.com.techjambo.springbootstudy.springbootcrudjpa.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.techjambo.springbootstudy.springbootcrudjpa.model.User;
import br.com.techjambo.springbootstudy.springbootcrudjpa.util.DBUtil;

@Component
public class UserDao {
	
	private List<User> users = DBUtil.listAllUsers();
	
	
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		
		if(user.getId() == null) {
			user.setId(DBUtil.userNextVal());
			users.add(user);
		}else {
			User userDB = findById(user.getId());
			userDB.setName(user.getName());
			userDB.setBirthDay(user.getBirthDay());
			
			users.set(getIndexByIdUser(user.getId()), userDB);
		}
		
		
		return user;
	}
	
	
	public User findById(Integer id) {
		for(User user: users) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}
	
	public void delete(Integer id) {
		Integer index =getIndexByIdUser(id);
		if(index!=null) {
			users.remove(index.intValue());			
		}

	}
	

	private Integer getIndexByIdUser(Integer id) {
		Integer index = null;
		if(users.size() >0) {
			index =0;
		}
		for(User user: users) {
			if(user.getId().equals(id)) {
				return index;
			}
			index ++;
		}
		return index;
	}
}
