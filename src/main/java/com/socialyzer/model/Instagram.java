package com.socialyzer.model;

import java.io.Serializable;

public class Instagram  extends SocialMedia implements Comparable<Instagram>,Serializable{
	public Instagram() {
		this.platform= "Instagram";
	}
	public Instagram(String username, String url, int followerCount)
	{
		super(username, url, followerCount);
		
		this.platform= "Instagram";
	}

	
	public  String getPlatform() {
		return platform;
	}
	@Override
	public String toString() {
		return "Instagram [username=" + username + ", url=" + url + ", followerCount=" + followerCount +this.platform+ "]";
	}
	@Override
	public int compareTo(Instagram o) {
		return this.getPlatform().compareTo(o.getPlatform());
	}
	
	

}
