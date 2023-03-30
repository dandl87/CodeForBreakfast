package com.protom.codeforbreakfast.model.entity;

public class SottoscrizionePost {
	
	private int id;
	private String username;
	private String password;
	private int postId;
	private int active;
	
	//costruttore di inserimento nuova sottoscrizione
	public SottoscrizionePost(String username, String password, int postId, int active) {
		super(); 
		this.username = username;
		this.password = password;
		this.postId = postId;
		this.active=active;
	}
	
	//costruttore di prelievo sottoscrizione
	public SottoscrizionePost(int id, String username, String password, int postId, int active) {
		super(); 
		this.id=id;
		this.username = username;
		this.password = password;
		this.postId = postId;
		this.active=active;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getPostId() {
		return postId;
	}


	public void setPostId(int postId) {
		this.postId = postId;
	}

	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "SottoscrizionePost [id=" + id + ", username=" + username + ", password=" + password + ", postId="
				+ postId + " active="+this.active+"]";
	}
	
	
	
	

}
