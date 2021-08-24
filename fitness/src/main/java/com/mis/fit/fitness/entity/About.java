package com.mis.fit.fitness.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "about")
public class About {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column				
	private String title;
	
	@Column(name = "small_description")
	private String smallDescription;
	
	@Column(name = "long_description")
	private String longDescription;
	
	@Column
	private String picture;
	
	public About() {
		
	}

	public About (String title, String smallDescription, String longDescription, String picture) {
		
		this.title = title;
		this.smallDescription = smallDescription;
		this.longDescription = longDescription;
		this.picture = picture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSmallDescription() {
		return smallDescription;
	}

	public void setSmallDescription(String smallDescription) {
		this.smallDescription = smallDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "About [id=" + id + ", title=" + title + ", smallDescription=" + smallDescription + ", longDescription="
				+ longDescription + ", picture=" + picture + "]";
	}
	
	
	
	

}
