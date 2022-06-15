package com.eaproject.blog.services;

import com.eaproject.blog.entities.Post;
import com.eaproject.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    // create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get all posts
    List<PostDto> getAllPost();

    // get post by id
    PostDto getPostById(Integer postId);

    // get all post by Category
    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all posts by User
    List<PostDto> getPostsByUser(Integer userId);

    // search posts
    List<Post> searchPosts(String keyword);
}
