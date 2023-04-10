package com.protom.codeforbreakfast.model.entity;

import java.sql.Date;

public class Conference {
	
	private int id;
	private String title;
	private String speaker;
	private String subTitle;
	private String link;
	private String linkImg;
	private String linkImgSmall;
	private Date data;
	private Date dataConference;
	private String timeOfConference;
	private String description;
	private int page;
	
	
	public Conference(String title, String speaker, String subTitle, String link, String linkImg, String linkImgSmall,  Date data, Date dataConference,
			String timeOfConference, String description, int page) {
		super();
		this.title = title;
		this.speaker = speaker;
		this.subTitle = subTitle;
		this.link = link;
		this.linkImg = linkImg;
		this.linkImgSmall = linkImgSmall;
		this.data = data;
		this.dataConference = dataConference;
		this.timeOfConference = timeOfConference;
		this.description = description;
		this.page = page;
	}
	
	public Conference(int id, String title, String speaker,  String subTitle, String link, String linkImg, String linkImgSmall, Date data, Date dataConference,
			String timeOfConference, String description, int page) {
		super();
		this.id=id;
		this.title = title;
		this.speaker = speaker;
		this.subTitle = subTitle;
		this.link = link;
		this.linkImg = linkImg;
		this.linkImgSmall = linkImgSmall;
		this.data = data;
		this.dataConference = dataConference;
		this.timeOfConference = timeOfConference;
		this.description=description;
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


	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
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
	
	public String getLinkImgSmall() {
		return linkImgSmall;
	}


	public void setLinkImgSmall(String linkImgSmall) {
		this.linkImgSmall = linkImgSmall;
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

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Conference [id=" + this.id + ", titolo=" + this.title + ", speaker ="+this.speaker+", subTitle="+this.subTitle+", link=" + this.link + ", linkImg=" + this.linkImg + ", data="
				+ this.data + ", dataConference=" + this.dataConference + ", timeOfConference=" + this.timeOfConference + ", description= "+this.description+", page="+this.page+"]";
	}
	
	

}
