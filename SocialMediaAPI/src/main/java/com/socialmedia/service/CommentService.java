package com.socialmedia.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.socialmedia.model.Comment;
import com.socialmedia.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Value("${jsonplaceholder.api.comments}")
	private String commentsApi;

	public List<Comment> getAllComments() {
		ResponseEntity<Comment[]> responseEntity = new RestTemplate().getForEntity(commentsApi, Comment[].class);
		Comment[] comments = responseEntity.getBody();

		commentRepository.saveAll(Arrays.asList(comments));

		return Arrays.asList(comments);
	}

	public Comment getCommentById(Long id) {
		return commentRepository.findById(id).orElseGet(() -> {
			String commentApiUrl = commentsApi + "/" + id;
			ResponseEntity<Comment> responseEntity = new RestTemplate().getForEntity(commentApiUrl, Comment.class);
			Comment comment = responseEntity.getBody();

			if (comment != null) {
				commentRepository.save(comment);
			}

			return comment;
		});
	}

	public Comment createComment(Comment comment) {
		// You may want to implement validation and additional logic here
		return commentRepository.save(comment);
	}

	public Comment updateComment(Long id, Comment updatedComment) {
		return commentRepository.findById(id).map(comment -> {
			comment.setName(updatedComment.getName());
			comment.setEmail(updatedComment.getEmail());
			comment.setBody(updatedComment.getBody());
			// Update other fields as needed
			return commentRepository.save(comment);
		}).orElse(null);
	}

	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}

}
