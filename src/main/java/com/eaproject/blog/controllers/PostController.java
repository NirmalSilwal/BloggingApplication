package com.eaproject.blog.controllers;

import com.eaproject.blog.entities.Post;
import com.eaproject.blog.payloads.ApiResponse;
import com.eaproject.blog.payloads.PostDto;
import com.eaproject.blog.repositories.PostRepo;
import com.eaproject.blog.services.CategoryService;
import com.eaproject.blog.services.PostService;
import com.eaproject.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    // create post
    @PostMapping("/user/{userId}/category/{catId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer catId) {
        PostDto createdNewPost = this.postService.createPost(postDto, userId, catId);

        return new ResponseEntity<PostDto>(createdNewPost, HttpStatus.CREATED);
    }

    // get posts by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> postDtos = this.postService.getPostsByUser(userId);

        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    // get posts by category
    @GetMapping("/category/{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer catId) {
        List<PostDto> postDtos = this.postService.getPostsByCategory(catId);

        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    // get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {

        List<PostDto> allPosts = this.postService.getAllPost(pageNumber, pageSize);
        return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
    }


    // get all posts details by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    // delete post

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("deleted post successfully!!", true);
    }

    // update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto givenPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(givenPost, HttpStatus.OK);
    }
}
