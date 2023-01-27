package com.personal.blogappapi.services;

import com.personal.blogappapi.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);
    void deleteComment(Integer commentId);
}
