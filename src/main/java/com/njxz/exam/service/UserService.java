package com.njxz.exam.service;

import java.util.List;

import com.njxz.exam.modle.User;

public interface UserService {
	public List<User> getAllUser();

	public List<User> findUsers(int page, int count);
	
	public List<User> findUsers(int power);

	public User findUser(String uId);

	public User findUser(String userName, String password);

	public int SaveUser(User user);
	
	public int updateUser(User user);
	
	public boolean deleteUser(Long uId);
	
	
}
