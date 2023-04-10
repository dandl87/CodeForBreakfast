package com.protom.codeforbreakfast.model.entity;

import java.sql.Date;
import java.util.ArrayList;

public class Post {
	
	private int id;
	private String title;
	private String subTitle;	
	private String link;
	private String linkImg;
	private String linkImgSmall;
	private String category;
	private Date data;
	private String description;
	private int page; 
	private ArrayList<SottoscrizionePost> listOfSubcriptions;
	
	
	//costruttore per la creazione di post
	public Post(String title, String subTitle, String link, String linkImg, String linkImgSmall, String category, Date data, String description, int page) {
		super(); 
		this.title = title;
		this.subTitle = subTitle;
		this.link = link;
		this.linkImg = linkImg;
		this.linkImgSmall = linkImgSmall;
		this.category = category;
		this.data = data;
		this.description=description;
		this.page=page; 
		this.listOfSubcriptions= new ArrayList<>();
	}
	
	//costruttore per il prelievo di post
		public Post(int id, String title, String subTitle, String link, String linkImg,String linkImgSmall, String category, Date data, String description, int page) {
			super();
			this.id=id;
			this.title = title;
			this.subTitle = subTitle;
			this.link = link;
			this.linkImg = linkImg;
			this.linkImgSmall = linkImgSmall;
			this.category = category;
			this.data = data;
			this.description=description;
			this.page=page; 
			this.listOfSubcriptions= new ArrayList<>();
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

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
 
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<SottoscrizionePost> getListOfSubcriptions() {
		return listOfSubcriptions;
	}


	public void setListOfSubcriptions(ArrayList<SottoscrizionePost> listOfSubcriptions) {
		this.listOfSubcriptions = listOfSubcriptions;
	}


	@Override
	public String toString() {
		return "Post [id=" + this.id + ", titolo=" + this.title + ",subTitle="+this.subTitle+", link=" + this.link + ", linkImg=" + this.linkImg +", linkImgSmall="+this.linkImgSmall+ ", category="
				+ this.category + ", data=" + this.data + ", description="+this.description+"+pagina="+this.page+", number of subscriptions= "+this.listOfSubcriptions.size()+"]";
	} 
	
	
	
	

}
