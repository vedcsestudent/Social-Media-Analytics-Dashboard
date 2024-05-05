package com.socialyzer.service;

import java.util.ArrayList;

import com.socialyzer.dao.CommentDao;
import com.socialyzer.model.Comment;
import com.socialyzer.util.DBConnection;

public class CommentService {
	private DBConnection dbConnection;
	private CommentDao commentDao ;
	
	public CommentService(DBConnection dbConnection)
	{
		this.dbConnection=dbConnection;
		commentDao= new CommentDao(dbConnection);
	}
	
	public Comment insert(Comment comment) throws Exception
	{
		return commentDao.insert(comment);
	}
	
	public boolean delete(String commentId) throws Exception
	{
		return commentDao.delete(commentId);
	}
	
	public ArrayList<Comment> findAll(String postId) throws Exception
	{
		return commentDao.findAllCommentByPost(postId);
	}
	
	public Comment findOne(String id) throws Exception
	{
		return commentDao.findOne(id);
	}
	
	public boolean update(String commentId) throws Exception
	{
		return  commentDao.update(commentId);
	}
	

}
