package com.socialyzer.service;

import java.util.ArrayList;

import com.socialyzer.dao.MentionDao;
import com.socialyzer.model.Mention;
import com.socialyzer.util.DBConnection;

public class MentionService {
	private DBConnection dbConnection;
	private MentionDao mentionDao;
	
	public MentionService(DBConnection dbConnection)
	{
		this.dbConnection=dbConnection;
		mentionDao= new MentionDao(dbConnection);
	}
	
	public Mention insert(Mention mention) throws Exception
	{
		return mentionDao.insert(mention);
	}
	

	public Mention findOne(String id ) throws Exception
	{
		return mentionDao.findOne(id);
	}
	

	public boolean delete(String id) throws Exception
	{
		return mentionDao.delete(id);
	}
	
	public boolean update(String id) throws Exception
	{
		return mentionDao.update(id);
	}
	
	public ArrayList<Mention> findAllMention(String id) throws Exception
	{
		return mentionDao.findAllMention(id);
	}
	
	
	

}
