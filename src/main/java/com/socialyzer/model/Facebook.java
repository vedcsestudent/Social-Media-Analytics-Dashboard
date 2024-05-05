package com.socialyzer.model;

import java.io.Serializable;

public class Facebook  extends SocialMedia implements Comparable<Facebook>,Serializable{
	public Facebook()
	{
		this.platform="FaceBook";
		
	}
	
	public Facebook(String username, String url, int followerCount)
	{
		super(username, url, followerCount);
		this.platform="Facebook";
	}
	

	public String getPlatform() {
		return platform;
	}

	@Override
	public String toString() {
		return "Facebook [username=" + username + ", url=" + url + ", followerCount=" + followerCount + this.platform+"]";
	}

	@Override
	public int compareTo(Facebook o) {
		return this.getPlatform().compareTo(o.getPlatform());
	}
	

}
