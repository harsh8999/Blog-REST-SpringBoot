package com.personal.blogappapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.blogappapi.entities.Category;
import com.personal.blogappapi.entities.Post;
import com.personal.blogappapi.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
    
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
