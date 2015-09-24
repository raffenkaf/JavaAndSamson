package com.samson.dao;

import java.util.List;

import com.samson.model.UserProfile;

public interface UserProfileDAO {
	
	public void addUser(UserProfile addUser);
	
	public void deleteUserBySessionId(String sessionId);
	
	public void deleteSession(String sessionId);
	
	public void addSessionId(String userName, String sessionId); 
	
	public List<UserProfile> getAllUsers();

	void deleteUserByUserName(String username);

	UserProfile getUserByUserName(String username);
}
