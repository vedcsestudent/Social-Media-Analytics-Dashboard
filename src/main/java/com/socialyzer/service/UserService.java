package com.socialyzer.service;

import java.util.ArrayList;

import com.socialyzer.dao.UserDao;
import com.socialyzer.model.Post;
import com.socialyzer.model.User;
import com.socialyzer.util.DBConnection;

public class UserService {
	
	private UserDao userDao;
	private DBConnection dbConnection;
	
	public UserService(DBConnection dbConnection)
	{
		this.dbConnection=dbConnection;
		userDao= new UserDao(dbConnection);
	}
	
	public User insert(User user) throws Exception
	{
		return userDao.insert(user);
	}
	

	public User findOne(String id) throws Exception
	{
		return userDao.findOne(id);
	}
	

	public boolean update( String id, User user) throws Exception
	{
		return userDao.update(id,user);
	}
	

	public boolean  delete(String id) throws Exception
	{
		return userDao.delete(id);
	}
	
	public ArrayList<User> findAll() throws Exception
	{
		return userDao.findAll();
	}

}
