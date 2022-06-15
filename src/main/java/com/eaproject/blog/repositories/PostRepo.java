package com.eaproject.blog.repositories;

import com.eaproject.blog.entities.Category;
import com.eaproject.blog.entities.Post;
import com.eaproject.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    public List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
