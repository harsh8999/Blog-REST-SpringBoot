package com.personal.blogappapi.services;

import java.util.List;

import com.personal.blogappapi.payloads.PostDto;
import com.personal.blogappapi.payloads.PostResponse;

public interface PostService {
    
    // create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get all
    // List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

    // get single post
    PostDto getPostById(Integer postId);

    // get all posts by Category
    List<PostDto> getAllPostsByCategory(Integer categoryId);

    // get all posts by User
    List<PostDto> getAllPostsByUser(Integer userId);

    // search posts 
    List<PostDto> searchPosts(String keyword);

}
