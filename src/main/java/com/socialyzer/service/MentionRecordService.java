package com.socialyzer.service;

import java.util.ArrayList;

import com.socialyzer.dao.MentionRecordDao;
import com.socialyzer.model.MentionRecord;
import com.socialyzer.util.DBConnection;

public class MentionRecordService {
	
	private DBConnection dbConnection;
	private MentionRecordDao  mentionRecordDao;
	
	public MentionRecordService(DBConnection dbConnection)
	{
		this.dbConnection=dbConnection;
		mentionRecordDao= new MentionRecordDao(dbConnection);
	}
	
	public MentionRecord insert(MentionRecord mentionRecord) throws Exception
	{
		return mentionRecordDao.insert(mentionRecord);
	}
	
	public MentionRecord findOne(String id) throws Exception
	{
		return mentionRecordDao.findOne(id);
	}
	
	public boolean delete(String id) throws Exception
	{
		return mentionRecordDao.delete(id);
	}
	
	public boolean  update(String id ) throws Exception
	{
		return mentionRecordDao.update(id);
	}
	
	public ArrayList<MentionRecord> findAllMentionRecord(String accountId) throws Exception
	{
		return mentionRecordDao.findAllMentionRecord(accountId);
	}
	

}
