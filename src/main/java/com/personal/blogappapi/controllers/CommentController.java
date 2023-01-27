package com.personal.blogappapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.blogappapi.payloads.ApiResponse;
import com.personal.blogappapi.payloads.CommentDto;
import com.personal.blogappapi.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer userId, @PathVariable Integer postId) {
        CommentDto result = this.commentService.createComment(commentDto, userId, postId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // DELETE
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully...", true), HttpStatus.OK);
    }
}
