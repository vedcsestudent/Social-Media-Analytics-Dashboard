package com.socialyzer.service;

import java.util.ArrayList;

import com.socialyzer.dao.AccountDao;
import com.socialyzer.model.Account;
import com.socialyzer.util.DBConnection;

public class AccountService {
	
	DBConnection dbconnection;
	AccountDao accountDao;
	
	public AccountService(DBConnection dbConnection)
	{
		this.dbconnection=dbConnection;
		 accountDao= new AccountDao(dbConnection);
	}
	
	public Account insert(Account account) throws Exception
	{
		return accountDao.insert(account);
	}
	
	public boolean update( String id, Account account) throws Exception
	{
		return accountDao.update(id, account);
	}
	
	public boolean delete(String id) throws Exception
	{
		return accountDao.delete(id);
	}
	
	public ArrayList<Account> findAll() throws Exception
	{
		return accountDao.findAll();
	}
	
	
	public ArrayList<Account> findByEmail(String email) throws Exception
	{
		return accountDao.findByEmail(email);
	}
	public Account findOne(String id) throws Exception
	{
		return accountDao.findOne(id);
	}
	
	
	public int findAvgFollower(ArrayList<Account> accountList) throws Exception
	{
		if(accountList.size()==0)
			return 0;
		int avg=0;
		int size= accountList.size();
		int sum=0;
		for(int i=0;i<accountList.size();i++)
		{
			sum+=accountList.get(i).getSocialMedia().getFollowerCount();
		}
		avg=sum/size;
		 return avg;
	}
	
	
	public int findAverageById(String email) throws Exception
	{
		ArrayList<Account> accountList=findByEmail(email);
		return findAvgFollower(accountList);
		
	}
	public ArrayList<Integer> findAllFollower(String email) throws Exception
	{
		ArrayList<Integer> ans= new ArrayList();
		ArrayList<Account> accountList= new ArrayList();
		accountList= findByEmail(email);
		
		for(Account account:accountList)
		{
			ans.add(account.getSocialMedia().getFollowerCount());
			
		}
		return ans;
		
	}

}
