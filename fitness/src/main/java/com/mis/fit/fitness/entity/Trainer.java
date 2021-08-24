package com.mis.fit.fitness.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trainer")
public class Trainer {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String trainings;
	@Column
	private String picture;
	@Column(name = "atindexpage")
	private Boolean atIndexPage;
	
	
	public Trainer() {
		
	}

	public Trainer(Long id, String name, String trainings, String picture) {
		
		this.name = name;
		this.trainings = trainings;
		this.picture = picture;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrainings() {
		return trainings;
	}

	public void setTrainings(String trainings) {
		this.trainings = trainings;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Boolean getAtIndexPage() {
		return atIndexPage;
	}

	public void setAtIndexPage(Boolean atIndexPage) {
		this.atIndexPage = atIndexPage;
	}

	@Override
	public String toString() {
		return "Trainer [id=" + id + ", name=" + name + ", trainings=" + trainings + ", picture=" + picture
				+ ", atIndexPage=" + atIndexPage + "]";
	}

	

	
}
