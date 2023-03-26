package com.protom.codeforbreakfast.model.entity;

public class SottoscrizioneConference {
	
	private int id;
	private String username;
	private String password;
	private int conferenceId;
	
	public SottoscrizioneConference(String username, String password, int conferenceId) {
		super(); 
		this.username = username;
		this.password = password;
		this.conferenceId = conferenceId;
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

	public int getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
	}

	@Override
	public String toString() {
		return "SottoscrizioneConference [id=" + id + ", username=" + username + ", password=" + password
				+ ", conferenceId=" + conferenceId + "]";
	}
	
	
	

}
