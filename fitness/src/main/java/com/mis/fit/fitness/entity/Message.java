package com.mis.fit.fitness.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column
	private String subject;
	
	@Column
	private String messageIndex;
	
	@Column(name = "seen_message")
	private Boolean seenMessage;
	
	public Message() {
		
	}

	public Message(String name, String email, String phoneNumber, String subject, String message, Boolean seenMessage) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.subject = subject;
		this.messageIndex = message;
		this.seenMessage = seenMessage;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageIndex() {
		return messageIndex;
	}

	public void setMessageIndex(String messageIndex) {
		this.messageIndex = messageIndex;
	}

	public Boolean getSeenMessage() {
		return seenMessage;
	}

	public void setSeenMessage(Boolean seenMessage) {
		this.seenMessage = seenMessage;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", subject=" + subject + ", message=" + messageIndex + ", seenMessage=" + seenMessage + "]";
	}
	
	
	
	

}
