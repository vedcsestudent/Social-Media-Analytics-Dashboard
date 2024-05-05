package com.socialyzer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.socialyzer.model.Account;
import com.socialyzer.model.Facebook;
import com.socialyzer.model.Follower;
import com.socialyzer.model.Instagram;
import com.socialyzer.model.SocialMedia;
import com.socialyzer.model.Twitter;
import com.socialyzer.model.User;
import com.socialyzer.util.DBConnection;

public class FollowerDao implements IDao<Follower>{
	Connection connection;
	DBConnection dbConnection;
	ArrayList<Follower> followerList= new ArrayList();
	public FollowerDao(DBConnection dbConnection)
	{
		this.dbConnection=dbConnection;
	}
	
	public  Follower insert(Follower follower) throws Exception
	{
			connection =dbConnection.getConnection();
			String sqlQuery= "insert into followers (account_id, followers_count, timestp) values (?,?,?)";
			PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, follower.getAccount().getAccountID());
			preparedStatement.setInt(2,follower.getFollowerCount());
			preparedStatement.setTimestamp(3, Timestamp.valueOf(follower.getTimeStp()));
			
			if(preparedStatement.executeUpdate()<0)
				return null;
			return follower;
	}
	
	
	public boolean delete(String accountId) throws Exception
	{	connection=dbConnection.getConnection();
		String sqlQuery="delete from followers where account_id=?";
		PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, accountId);
		if(preparedStatement.executeUpdate()<0)
			return false;
		
		return true;
	}
	
	
	public boolean update(Follower follower) throws Exception
	{
			connection =dbConnection.getConnection();
			String sqlQuery= "update followers set followers_count=?, timestp=? where account_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, follower.getFollowerCount());
			preparedStatement.setTimestamp(2,Timestamp.valueOf(follower.getTimeStp()));
			preparedStatement.setString(3, follower.getAccount().getAccountID());
			if(preparedStatement.executeUpdate()<0)
				return false;
			return true;
	}
	
	
	public ArrayList<Follower> findAll() throws Exception
	{
		connection=dbConnection.getConnection();
		String sqlQuery= "select * from followers";
		PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
		ResultSet resultSet= preparedStatement.executeQuery();
		while(resultSet.next()) {
			String accountId= resultSet.getString("account_id");
			int followerCount= resultSet.getInt("followers_count");
			Timestamp timeStamp= resultSet.getTimestamp("timeStp");
			Account account= new Account();
			account.setAccountID(accountId);
			Follower follower= new Follower(account,followerCount, timeStamp.toLocalDateTime());
			followerList.add(follower);
		}
		return followerList;
	}
	public ArrayList<Follower> findByEmail(String email) throws Exception
	{
		connection=dbConnection.getConnection();
		String sqlQuery= "select f.account_id, f.timestp,f.followers_count from accounts a inner join followers f on a.account_id=f.account_id where a.email=?";
		PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, email);
		ResultSet resultSet= preparedStatement.executeQuery();
		while(resultSet.next()) {
			String accountId= resultSet.getString("account_id");
			int followerCount= resultSet.getInt("followers_count");
			Timestamp timeStamp= resultSet.getTimestamp("timeStp");
			String sqlQuery2="select * from accounts where account_id=?";
			PreparedStatement preparedStatement2= connection.prepareStatement(sqlQuery2);
			preparedStatement2.setString(1, accountId);
			ResultSet resultSet2=preparedStatement2.executeQuery();
			Account account= new Account();
			if(resultSet2.next()) {
				Timestamp time=resultSet2.getTimestamp("date_joined");
				account.setDateJoined(time.toLocalDateTime());
				String platform=resultSet2.getString("platform");
				String username= resultSet2.getString("username");
				String url= resultSet2.getString("account_url");
				SocialMedia socialMedia;
				
				if(platform.equalsIgnoreCase("Twitter"))
				{
					socialMedia= new Twitter(username,url, followerCount);
				}
				else if(platform.equalsIgnoreCase("Facebook"))
				{
					socialMedia= new Facebook(username, url, followerCount);
				}
				else
				{
					socialMedia= new Instagram(username, url, followerCount);
				}
				account.setSocialMedia(socialMedia);
				String sqlQuery3= "select * from users where email=?";
				
				PreparedStatement preparedStatement3=connection.prepareStatement(sqlQuery3);
				preparedStatement3.setString(1, email);
				ResultSet resultSet3= preparedStatement3.executeQuery();
				User user;
				if(resultSet3.next())
				user= new User(email,resultSet3.getString("password"));
				else
					user= new User(email,null);
				account.setUser(user);
				
				
			
				
			}
			
			account.setAccountID(accountId);
			
			
			Follower follower= new Follower(account,followerCount, timeStamp.toLocalDateTime());
			followerList.add(follower);
		}
		return followerList;
	}
	
	
	

	@Override
	public Follower findOne(String id) throws Exception {
		return null;
	}
	
	
	
	
	

}
