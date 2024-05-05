package com.socialyzer.service;

import java.util.ArrayList;

import com.socialyzer.dao.PostDao;
import com.socialyzer.model.Post;
import com.socialyzer.util.DBConnection;

public class PostService {
	private DBConnection dbConnection;
	private PostDao postDao;
	
	public PostService(DBConnection dbConnection)
	{
		this.dbConnection=dbConnection;
		postDao= new PostDao(dbConnection);
	}
	
	public Post insert(Post post) throws Exception
	{
		return postDao.insert(post);
	}
	
	public boolean  update(Post post) throws Exception
	{
		return postDao.update(post);
	}
	
	public boolean delete(String postId) throws Exception
	{
		return postDao.delete(postId);
	}
	
	public Post  findOne(String postId) throws Exception
	{
		return postDao.findOne(postId);
	}
	
	public ArrayList<Post> findAllPost() throws Exception
	{
		return postDao.findAllPost();
	}
	
	public ArrayList<Post> findAllPostById(String id ) throws Exception
	{
		return postDao.findAllPostById(id);
	}
	
	public ArrayList<Post> findAllPostByEmail(String email) throws Exception
	{
		return postDao.findAllPostByEmail(email);
	}
	public int avgLikeCount(ArrayList<Post> postList) throws Exception
	{
		if(postList.size()==0)
			return 0;
		int avg=0;
		int sum=0;
		int n= postList.size();
		for(int i=0;i<postList.size();i++)
		{
			sum+=postList.get(0).getLikeCount();
		}

		avg=(sum/n);
		return avg;
	}
	
	
	public int avgShareCount(ArrayList<Post> postList) throws Exception
	{
		if(postList.size()==0)
			return 0;
		int avg=0;
		int sum=0;
		int n= postList.size();
		for(int i=0;i<postList.size();i++)
		{
			sum+=postList.get(0).getShareCount();
		}
		avg=(sum/n);
		return avg;
	}
	
	public int avgSentimentScore(ArrayList<Post> postList) throws Exception
	{if(postList.size()==0)
		return 0;
		int avg=0;
		int sum=0;
		int n= postList.size();
		for(int i=0;i<postList.size();i++)
		{
			sum+=postList.get(0).getPostScore();
		}
		avg=(sum/n);

		return avg;
	}
	
	public ArrayList<Post> findAllByDate(String date) throws Exception
	{
		return postDao.findAllPostByDate(date);
	}
	
	public ArrayList<Post> findAllByKeyword(String keyword) throws Exception
	{
		return postDao.findAllPostByKeyword(keyword);
	}
	
	

}
