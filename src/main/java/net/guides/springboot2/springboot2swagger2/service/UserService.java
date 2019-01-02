package net.guides.springboot2.springboot2swagger2.service;

import java.util.List;

import net.guides.springboot2.springboot2swagger2.model.User;

public interface UserService {

	
	//Get All User. 
	public List<User> getAllUser();
	
	//To create a User
	public  User createUser(User user);
	
	
	//To update a User
	public User updateUser(User user);
	
	//To Delete a User
	
	public void deleteUser(User user);
	
	//To GetUser By Id
	public User getUser(Integer userID);
}
