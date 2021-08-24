package com.mis.fit.fitness.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "slider")
public class Slider {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String image;
	
	@Column(name = "slider_title")
	private String sliderTitle;
	
	@Column(name = "link_title")
	private String linkTitle;
	
	@Column
	private String description;
	
	public Slider() {
		
	}

	public Slider(String image, String sliderTitle, String linkTitle, String description) {
		
		this.image = image;
		this.sliderTitle = sliderTitle;
		this.linkTitle = linkTitle;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSliderTitle() {
		return sliderTitle;
	}

	public void setSliderTitle(String sliderTitle) {
		this.sliderTitle = sliderTitle;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Slider [id=" + id + ", image=" + image + ", sliderTitle=" + sliderTitle + ", linkTitle=" + linkTitle
				+ ", description=" + description + "]";
	}
	
	
	
	

}
