package com.socialyzer.service;

import java.util.ArrayList;

import com.socialyzer.dao.CompetitorDao;
import com.socialyzer.model.Competitor;
import com.socialyzer.util.DBConnection;

public class CompetitorService {
	private DBConnection dbConnection;
	private CompetitorDao competitorDao;
	
	public CompetitorService(DBConnection dbConnection)
	{
		this.dbConnection=dbConnection;
		competitorDao= new CompetitorDao(dbConnection);
	}
	
	public Competitor insert(Competitor competitor) throws Exception
	{
		return competitorDao.insert(competitor);
	}
	
	public boolean delete(String id) throws Exception
	{
		return competitorDao.delete(id);
	}
	
	public ArrayList<Competitor> findAllCompetitorById(String id) throws Exception
	{
		return competitorDao.findAllCompetitorById(id);
	}
	
	public Competitor findOne(String id) throws Exception
	{
		return competitorDao.findOne(id);
	}

}
