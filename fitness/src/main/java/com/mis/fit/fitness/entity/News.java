package com.mis.fit.fitness.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "small_description")
	private String smallDescription;
	
	@Column(name = "long_description")
	private String longDescription;
	
	@Column
	private String picture;
	
	public News() {
		
	}

	public News(String smallDescription, String largeDescription, String picture) {
		
		this.smallDescription = smallDescription;
		this.longDescription = largeDescription;
		this.picture = picture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setLongDescription(String largeDescription) {
		this.longDescription = largeDescription;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", smallDescription=" + smallDescription + ", largeDescription=" + longDescription
				+ ", picture=" + picture + "]";
	}
	
	

}
