package com.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia.model.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
