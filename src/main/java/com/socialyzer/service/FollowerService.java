package com.socialyzer.service;

import java.util.ArrayList;

import com.socialyzer.dao.FollowerDao;
import com.socialyzer.model.Follower;
import com.socialyzer.util.DBConnection;

public class FollowerService {
	private DBConnection dbConnection;
	private FollowerDao followerDao;
	
	public FollowerService(DBConnection dbConnection)
	{
		this.dbConnection= dbConnection;
		followerDao= new FollowerDao(dbConnection);
	}
	
	public  Follower insert(Follower follower) throws Exception
	{
		return followerDao.insert(follower);
	}
	
	public  boolean delete(String accountId) throws Exception
	{
		return followerDao.delete(accountId);
	}
	
	public boolean update(Follower follower) throws Exception
	{
		return followerDao.update(follower);
		
	}
	
	public ArrayList<Integer> findByPlatform(ArrayList<Follower>followerList,String platform) throws Exception
	{
		
		ArrayList<Integer>  followerCountList=new ArrayList();
		for(Follower follower: followerList)
		{
			if(follower.getAccount().getSocialMedia().getPlatform().equalsIgnoreCase(platform))
				followerCountList.add(follower.getFollowerCount());
		}
		return followerCountList;
	}
	

	
	public  ArrayList<Follower> findAll() throws Exception
	{
		return followerDao.findAll();
	}
	
	public ArrayList<Follower> findAllByEmail(String email) throws Exception
	{
		return followerDao.findByEmail(email);
	}
	

}
