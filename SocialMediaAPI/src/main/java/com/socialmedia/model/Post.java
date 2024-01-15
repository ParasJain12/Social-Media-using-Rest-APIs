package com.socialmedia.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	private String title;
	private String body;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(Long id, User user, String title, String body) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.body = body;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
