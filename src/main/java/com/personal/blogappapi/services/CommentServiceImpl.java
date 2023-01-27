package com.personal.blogappapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.blogappapi.entities.Comment;
import com.personal.blogappapi.entities.Post;
import com.personal.blogappapi.entities.User;
import com.personal.blogappapi.exceptions.ResourceNotFoundException;
import com.personal.blogappapi.payloads.CommentDto;
import com.personal.blogappapi.repository.CommentRepo;
import com.personal.blogappapi.repository.PostRepo;
import com.personal.blogappapi.repository.UserRepo;

@Service 
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer userId , Integer postId) {
        User user = this.userRepo.findById(userId)
                                    .orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
        Post post = this.postRepo.findById(postId)
                                    .orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId)
                                            .orElseThrow(()->new ResourceNotFoundException("Comment", "Comment Id", commentId));

        this.commentRepo.delete(comment);  
    } 
}
