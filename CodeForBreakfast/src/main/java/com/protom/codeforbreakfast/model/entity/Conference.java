package com.protom.codeforbreakfast.model.entity;

import java.sql.Date;

public class Conference {
	
	private int id;
	private String title;
	private String link;
	private String linkImg;
	private Date data;
	private Date dataConference;
	private String timeOfConference;
	private int page;
	
	
	public Conference(String title, String link, String linkImg, Date data, Date dataConference,
			String timeOfConference, int page) {
		super();
		this.title = title;
		this.link = link;
		this.linkImg = linkImg;
		this.data = data;
		this.dataConference = dataConference;
		this.timeOfConference = timeOfConference;
		this.page = page;
	}
	
	public Conference(int id, String title, String link, String linkImg, Date data, Date dataConference,
			String timeOfConference, int page) {
		super();
		this.id=id;
		this.title = title;
		this.link = link;
		this.linkImg = linkImg;
		this.data = data;
		this.dataConference = dataConference;
		this.timeOfConference = timeOfConference;
		this.page=page;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String getLinkImg() {
		return linkImg;
	}


	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public Date getDataConference() {
		return dataConference;
	}


	public void setDataConference(Date dataConference) {
		this.dataConference = dataConference;
	}


	public String getTimeOfConference() {
		return timeOfConference;
	}


	public void setTimeOfConference(String timeOfConference) {
		this.timeOfConference = timeOfConference;
	}

	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Conference [id=" + this.id + ", titolo=" + this.title + ", link=" + this.link + ", linkImg=" + this.linkImg + ", data="
				+ this.data + ", dataConference=" + this.dataConference + ", timeOfConference=" + this.timeOfConference + " page="+this.page+"]";
	}
	
	

}
