package com.eaproject.blog.controllers;

import com.eaproject.blog.entities.Post;
import com.eaproject.blog.payloads.PostDto;
import com.eaproject.blog.repositories.PostRepo;
import com.eaproject.blog.services.CategoryService;
import com.eaproject.blog.services.PostService;
import com.eaproject.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
