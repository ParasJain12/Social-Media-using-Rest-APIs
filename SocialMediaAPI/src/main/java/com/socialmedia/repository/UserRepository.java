package com.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
