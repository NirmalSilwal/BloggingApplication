package com.eaproject.blog.services;

import com.eaproject.blog.entities.Post;
import com.eaproject.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    // create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update
    Post updatePost(PostDto postDto, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get all posts
    List<Post> getAllPost();

    // get post by id
    Post getPostById(Integer postId);

    // get all post by Category
    List<Post> getPostsByCategory(Integer categoryId);

    // get all posts by User
    List<Post> getPostsByUser(Integer userId);

    // search posts
    List<Post> searchPosts(String keyword);
}
