package com.socialyzer.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.socialyzer.model.Account;
import com.socialyzer.model.Post;
import com.socialyzer.model.User;
import com.socialyzer.util.DBConnection;

import oracle.jdbc.OracleTypes;

public class PostDao  implements IDao<Post>{
	
	Connection connection;
	DBConnection dbConnection;
	ArrayList<Post> postList= new ArrayList();
	
	public PostDao(DBConnection dbConnection)
	{
		this.dbConnection= dbConnection;
	}
	
	public Post insert(Post post) throws Exception
	{
		connection= dbConnection.getConnection();
		String sqlQuery="insert into posts ( account_id, post_text, like_count, share_count, post_time, post_score) values(?,?,?,?,?,?)";
		PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
		
		preparedStatement.setString(1, post.getAccount().getAccountID());
		preparedStatement.setString(2, post.getPostText());
		preparedStatement.setInt(3, post.getLikeCount());
		preparedStatement.setInt(4, post.getShareCount());
		preparedStatement.setTimestamp(5, Timestamp.valueOf(post.getPostTime()));
		preparedStatement.setFloat(6,  post.getPostScore());
		
		if(preparedStatement.executeUpdate()<0)
			return null;
		
		return post;
	}

	
	public boolean update(Post post) throws Exception
	{	connection =dbConnection.getConnection();
		String sqlQuery= "update Posts set  like_count=?, share_count=? where post_id=?";
		PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1,  post.getLikeCount());
		preparedStatement.setInt(2,post.getShareCount());
		preparedStatement.setString(3, post.getPostId());
		
		if(preparedStatement.executeUpdate()<0)
			return false;
		
		return true;
	}
	
	
	public boolean delete(String postId) throws Exception
	{	
		connection=dbConnection.getConnection();
		String sqlQuery="delete from posts where post_id=?";
		PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1,  postId);
		if(preparedStatement.executeUpdate()<0)
			return false;
		return true;
	}
	@Override
	public Post findOne(String id) throws Exception {
		
		connection=dbConnection.getConnection();
		String sqlQuery="select * from posts where post_id=?";
		PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, id);
		ResultSet resultSet= preparedStatement.executeQuery();
		if(resultSet.next()) { 
			Post post= new Post();
			String accountId=resultSet.getString("account_id");
			String postText= resultSet.getString("post_text");
			int likeCount=resultSet.getInt("like_count");
			int shareCount= resultSet.getInt("share_count");
			LocalDateTime timestamp = resultSet.getTimestamp("post_time").toLocalDateTime();
			float postScore = resultSet.getFloat("post_score");
			
			post.setPostId(id);
			Account account= new Account();
			account.setAccountID(accountId);
			post.setAccount(account);
			post.setLikeCount(likeCount);
			post.setShareCount(shareCount);
			post.setPostTime(timestamp);
			post.setPostScore(postScore);
			
			
			return post;
			
		}
		return null;
	}
	
	public ArrayList<Post> findAllPost() throws Exception
	{
		connection=dbConnection.getConnection();
		String sqlQuery= "select  * from posts";
		PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
		ResultSet resultSet= preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			
			Post post= new Post();
			String accountId=resultSet.getString("account_id");
			String postText= resultSet.getString("post_text");
			int likeCount=resultSet.getInt("like_count");
			int shareCount= resultSet.getInt("share_count");
			LocalDateTime timestamp = resultSet.getTimestamp("post_time").toLocalDateTime();
			float postScore = resultSet.getFloat("post_score");
			
			post.setPostId(resultSet.getString("post_id"));
			Account account= new Account();
			account.setAccountID(accountId);
			post.setAccount(account);
			post.setLikeCount(likeCount);
			post.setShareCount(shareCount);
			post.setPostTime(timestamp);
			post.setPostScore(postScore);
			postList.add(post);
			
			
		}
		return postList;
	}
	
	public ArrayList<Post> findAllPostById(String accountid) throws Exception
	{
		connection=dbConnection.getConnection();
		String sqlQuery= "select  * from posts where account_id=?";
		
		PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, accountid);
		ResultSet resultSet= preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			
			Post post= new Post();
			String accountId=resultSet.getString("account_id");
			String postText= resultSet.getString("post_text");
			int likeCount=resultSet.getInt("like_count");
			int shareCount= resultSet.getInt("share_count");
			LocalDateTime timestamp = resultSet.getTimestamp("post_time").toLocalDateTime();
			float postScore = resultSet.getFloat("post_score");
			
			post.setPostId(resultSet.getString("post_id"));
			Account account= new Account();
			account.setAccountID(accountId);
			post.setAccount(account);
			post.setLikeCount(likeCount);
			post.setShareCount(shareCount);
			post.setPostTime(timestamp);
			post.setPostScore(postScore);
			postList.add(post);
			
			
		}
		return postList;
	}
	
	public ArrayList<Post> findAllPostByEmail(String  email) throws Exception
	{
		connection=dbConnection.getConnection();
		String sqlQuery= "select p.account_id account_id ,a.platform platform , a.email email, p.post_id post_id, p.post_text post_text, p.like_count like_count, p.share_count share_count, p.post_time post_time, p.post_score post_score from posts p inner join accounts a on p.account_id=a.account_id where a.email=?";
		
		PreparedStatement preparedStatement= connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, email);
		ResultSet resultSet= preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			
			Post post= new Post();
			String accountId=resultSet.getString("account_id");
			String postText= resultSet.getString("post_text");
			int likeCount=resultSet.getInt("like_count");
			int shareCount= resultSet.getInt("share_count");
			LocalDateTime timestamp = resultSet.getTimestamp("post_time").toLocalDateTime();
			float postScore = resultSet.getFloat("post_score");
			
			post.setPostId(resultSet.getString("post_id"));
			Account account= new Account();
			account.setAccountID(accountId);
			post.setAccount(account);
			User user= new User();
			user.setEmail(email);
			account.setUser(user);//user set the email
			
			post.setAccount(account);//account showing the accountid
			post.setLikeCount(likeCount);
			post.setShareCount(shareCount);
			post.setPostTime(timestamp);
			post.setPostScore(postScore);
			post.setPostText(postText);
			postList.add(post);
			
			
		}
		return postList;
	}
	
	
	public ArrayList<Post> findAllPostByDate(String date) throws Exception
	{
		connection=dbConnection.getConnection();
		// working query :select to_date(to_char(post_time, 'dd/mm/yyyy')) from posts where to_date('?') <=to_date(to_char(post_time, 'dd/mm/yyyy')) and  to_date(to_char(systimestamp, 'dd/mm/yyyy'))>=to_date(to_char(post_time, 'dd/mm/yyyy'));

		String sqlQuery="select * from posts p inner join accounts a on a.accountId= p.postid where post_time>= to_timestamp(?,'DD-MM-YY HH24:MI:SS') and post_time<=systimestamp";
		PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, date);
		ResultSet resultSet= preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			String postId=resultSet.getString("post_id");
			String postText=resultSet.getString("post_text");
			String accountId= resultSet.getString("account_id");
			float postScore= resultSet.getFloat("post_score");
			int likeCount=resultSet.getInt("like_count");
			int shareCount=resultSet.getInt("share_count");
			Timestamp time =resultSet.getTimestamp("followers_count");
			
			Account account= new Account();
			PreparedStatement preparedStatement2= connection.prepareStatement("select distinct a.email from accounts a inner join posts p on a.account_id=p.account_id where a.account_id=?");
			ResultSet resultSet2 = preparedStatement2.executeQuery();
			User user= new User();
			if(resultSet2.next()) {
				user.setEmail(resultSet2.getString("email"));
			}
			resultSet2.close();
			account.setUser(user);
			account.setAccountID(accountId);
			Post post= new Post();
			post.setAccount(account);
			post.setLikeCount(likeCount);
			post.setShareCount(shareCount);
			post.setPostScore(postScore);
			post.setPostTime(time.toLocalDateTime());
			postList.add(post);
		}
		resultSet.close();
		System.out.println("by time");
		for(Post post: postList)
		{
			System.out.println(post);
			
		}
		return postList;
	}
	
	public ArrayList<Post> findAllPostByKeyword(String text) throws Exception
	{
		connection=dbConnection.getConnection();
		String sqlQuery="{ ? = call get_listofpost_ofparticular_acc(?) }";
		CallableStatement callableStatement= connection.prepareCall(sqlQuery);
	 callableStatement.registerOutParameter(1,  OracleTypes.CURSOR);
	 callableStatement.setString(2, text);
	 callableStatement.execute();
	 ResultSet resultSet= (ResultSet)callableStatement.getObject(1);

	 
		while(resultSet.next()) {
			Post post= new Post();
			String accountId= resultSet.getString("account_id");
			String postText= resultSet.getString("post_text");
			String postId= resultSet.getString("post_id");
			int likeCount= resultSet.getInt("like_count");
			int shareCount=resultSet.getInt("share_count");
			float postScore= resultSet.getInt("post_score");
			Timestamp time=resultSet.getTimestamp("post_time");
			
			Account account= new Account();
			account.setAccountID(accountId);
			post.setAccount(account);
			post.setLikeCount(likeCount);
			post.setShareCount(shareCount);
			post.setPostScore(postScore);
			post.setPostText(postText);
			post.setPostTime(time.toLocalDateTime());
		
			post.setPostId(postId);
			postList.add(post);
		}
		for(Post post:postList)
		{
			System.out.println(post);
		}
		return postList;
	}
	
	
	

}
