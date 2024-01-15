package com.socialmedia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	private Long id;
	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
	private String name;
	private String email;
	private String body;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Long id, Post post, String name, String email, String body) {
		super();
		this.id = id;
		this.post = post;
		this.name = name;
		this.email = email;
		this.body = body;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
