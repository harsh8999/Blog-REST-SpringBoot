package com.personal.blogappapi.controllers;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.personal.blogappapi.config.AppConstants;
import com.personal.blogappapi.payloads.ApiResponse;
import com.personal.blogappapi.payloads.PostDto;
import com.personal.blogappapi.payloads.PostResponse;
import com.personal.blogappapi.services.FileService;
import com.personal.blogappapi.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    
    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, 
                                                @PathVariable Integer userId, 
                                                @PathVariable Integer categoryId) {
        PostDto createPostDto = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<>(createPostDto, HttpStatus.CREATED);
    }

    // updatePost
    // update
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, 
                                                @PathVariable Integer postId) {
        PostDto createPostDto = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<>(createPostDto, HttpStatus.OK);
    }


    // delete
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        ApiResponse apiResponse = new ApiResponse("Post deleted successfully!!!", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // get all
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getPosts(
                                        @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                        @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                        @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                        @RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection
                                      ) {
        // List<PostDto> postDtos = this.postService.getAllPosts(pageNumber, pageSize);
        PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // get by Id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) {
        PostDto postDtos = this.postService.getPostById(postId);
        return ResponseEntity.ok(postDtos);
    }

    // get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(
                                                    @PathVariable Integer userId
                                                    ){
        List<PostDto> postDtos = this.postService.getAllPostsByUser(userId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }


    // get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(
                                                    @PathVariable Integer categoryId
                                                    ){
        List<PostDto> postDtos = this.postService.getAllPostsByCategory(categoryId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }


    // search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(
                                        @PathVariable("keywords") String keywords
                                        ) {
        List<PostDto> postDtos = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    // post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<String> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);

        // set it to db
        postDto.setImageName(fileName);
        this.postService.updatePost(postDto, postId);

        // return new ResponseEntity<>(postDto, HttpStatus.OK);
        return new ResponseEntity<>("File Uploaded", HttpStatus.OK);
    }


    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException{
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
    // localhost:8080/images/abc.png

    


}