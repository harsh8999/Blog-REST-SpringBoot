package com.personal.blogappapi.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private int postId;

    @NotEmpty
    private String title;

    private String content;

    private String imageName;

    private UserDto user;

    private CategoryDto category;
     
    private Date addedDate;

    private Set<CommentDto> comments = new HashSet<>();

}
