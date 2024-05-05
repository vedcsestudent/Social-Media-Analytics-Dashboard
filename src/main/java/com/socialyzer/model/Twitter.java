package com.socialyzer.model;

import java.io.Serializable;

public class Twitter extends SocialMedia implements Comparable<Twitter>,Serializable {

	
	
	

	public Twitter() {
		super();
	}
	
	public Twitter(String username, String url, int followerCount) {
		super(username, url, followerCount);
		this.platform="Twitter";
	}

	public  String getPlatform() {
		return platform; 
	}




	@Override
	public String toString() {
		return "Twitter [username=" + username + ", url=" + url + ", followerCount=" + followerCount +platform+ "]";
	}

	@Override
	public int compareTo(Twitter o) {
		return  this.getPlatform().compareTo(o.getPlatform());
	}
	
	

}
