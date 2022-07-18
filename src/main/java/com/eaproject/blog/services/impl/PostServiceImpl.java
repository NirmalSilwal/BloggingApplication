package com.eaproject.blog.services.impl;

import com.eaproject.blog.entities.Category;
import com.eaproject.blog.entities.Post;
import com.eaproject.blog.entities.User;
import com.eaproject.blog.exceptions.ResourceNotFoundException;
import com.eaproject.blog.payloads.PostDto;
import com.eaproject.blog.payloads.PostResponse;
import com.eaproject.blog.payloads.UserDto;
import com.eaproject.blog.repositories.CategoryRepo;
import com.eaproject.blog.repositories.PostRepo;
import com.eaproject.blog.repositories.UserRepo;
import com.eaproject.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post mypost = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

        mypost.setTitle(postDto.getTitle());
        mypost.setContent(postDto.getContent());
        mypost.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(mypost);

        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
//        this.postRepo.deleteById(postId);

        Post deletedPost = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

        this.postRepo.delete(deletedPost);
    }


    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
        Pageable pg = PageRequest.of(pageNumber, pageSize);

        Page<Post> pagePosts = this.postRepo.findAll(pg);
        List<Post> allPosts = pagePosts.getContent();

        List<PostDto> postDtos = allPosts.stream().map(p -> this.modelMapper
                .map(p, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        List<Post> allPosts = this.postRepo.findByCategory(cat);

        List<PostDto> postDtos = allPosts.stream().map(p ->
                this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        List<Post> allPosts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = allPosts.stream().map(p ->
                this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
