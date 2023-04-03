package com.protom.codeforbreakfast.model.entity;

public class SottoscrizioneConference {
	
	private int id;
	private String username;
	private String password;
	private Conference conference;
	
	public SottoscrizioneConference(String username, String password, Conference conference) {
		super(); 
		this.username = username;
		this.password = password;
		this.conference = conference;
	}
	
	public SottoscrizioneConference(int id, String username, String password, Conference conference) {
		super(); 
		this.id=id;
		this.username = username;
		this.password = password;
		this.conference = conference;
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

	public Conference getConference() {
		return conference;
	}

	public void setConferenceId(Conference conference) {
		this.conference = conference;
	}

	@Override
	public String toString() {
		return "SottoscrizioneConference [id=" + id + ", username=" + username + ", password=" + password
				+ ",  conference=" + conference.getId() + "]";
	}
	
	
	

}
