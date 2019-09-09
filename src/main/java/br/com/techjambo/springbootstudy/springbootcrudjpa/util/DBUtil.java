package br.com.techjambo.springbootstudy.springbootcrudjpa.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.techjambo.springbootstudy.springbootcrudjpa.model.User;

public class DBUtil {

	private static List<User> users = new ArrayList<User>();

	private static int usersCount = 4;

	static {
		users.add(new User(1, "Joe", new Date()));
		users.add(new User(2, "Julia", new Date()));
		users.add(new User(3, "Mary", new Date()));
		users.add(new User(4, "Joseph", new Date()));
	}

	public static List<User> listAllUsers() {
		return users;
	}
	
	public static Integer userNextVal() {
		return ++usersCount;
	}
	
}
