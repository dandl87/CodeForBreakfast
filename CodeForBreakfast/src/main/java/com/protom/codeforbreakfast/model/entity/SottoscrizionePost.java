package com.protom.codeforbreakfast.model.entity;

public class SottoscrizionePost {
	
	private int id;
	private String username;
	private Post post;
	private int position; 
	
	//costruttore di inserimento nuova sottoscrizione
	public SottoscrizionePost(String username,Post post, int position) {
		super(); 
		this.username = username;
		this.post = post;
		this.position=position; 
	}
	
	//costruttore di prelievo sottoscrizione
	public SottoscrizionePost(int id, String username,Post post, int position) {
		super(); 
		this.id=id;
		this.username = username;
		this.post = post;
		this.position=position; 
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





	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}

	
	 
	
	

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "SottoscrizionePost [id=" + id + ", username=" + username + ", postId="
				+ post.getId() + "position:"+this.position+"]";
	}
	
	
	
	

}
