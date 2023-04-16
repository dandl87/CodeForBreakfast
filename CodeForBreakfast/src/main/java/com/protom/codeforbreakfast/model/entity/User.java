package com.protom.codeforbreakfast.model.entity;

import java.util.ArrayList;

public class User {
	
	private String username;
	private String password;
	private String name;
	private String surname;
	private String email;
	private ArrayList<SottoscrizionePost> sottoscrizioniPost;
	private ArrayList<SottoscrizioneConference> sottoscrizioniConference;
	
	
	
	public User(String username, String password, String name, String surname, String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email=email;
		sottoscrizioniPost= new ArrayList<>(6);
		sottoscrizioniConference = new ArrayList<>(6);
	}
	
	//for membership section
	public User(String username,String name, String surname, String email) {
		super();
		this.username = username; 
		this.name = name;
		this.surname = surname;
		this.email=email;
		sottoscrizioniPost= new ArrayList<>(6);
		sottoscrizioniConference = new ArrayList<>(6);
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return this.surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}
	 
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<SottoscrizionePost> getSottoscrizioniPost() {
		return sottoscrizioniPost;
	}


	public void setSottoscrizioniPost(ArrayList<SottoscrizionePost> sottoscrizioniPost) {
		this.sottoscrizioniPost = sottoscrizioniPost;
	}


	public ArrayList<SottoscrizioneConference> getSottoscrizioniConference() {
		return sottoscrizioniConference;
	}


	public void setSottoscrizioniConference(ArrayList<SottoscrizioneConference> sottoscrizioniConference) {
		this.sottoscrizioniConference = sottoscrizioniConference;
	}


	@Override
	public String toString() {
		return "User [username=" + this.username + ", password=" + this.password + ", name=" + this.name + ", surname=" + this.surname
				+ "email= "+email+" numero sottoscrizioni post:"+ this.sottoscrizioniPost.size()+" numero sottoscrizioni Conference:"+this.sottoscrizioniConference.size()+"]";
	}
	
	
	

}
