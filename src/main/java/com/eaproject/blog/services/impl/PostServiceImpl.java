package com.eaproject.blog.services.impl;

import com.eaproject.blog.entities.Category;
import com.eaproject.blog.entities.Post;
import com.eaproject.blog.entities.User;
import com.eaproject.blog.exceptions.ResourceNotFoundException;
import com.eaproject.blog.payloads.PostDto;
import com.eaproject.blog.payloads.UserDto;
import com.eaproject.blog.repositories.CategoryRepo;
import com.eaproject.blog.repositories.PostRepo;
import com.eaproject.blog.repositories.UserRepo;
import com.eaproject.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        Category category = this.categoryRepo
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        // note that title and content of post are saved through post dto object

        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);

        Post savedPost = this.postRepo.save(post);

        return this.modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
