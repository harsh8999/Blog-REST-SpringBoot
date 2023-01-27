package com.personal.blogappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.blogappapi.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
    
}
