package com.eaproject.blog.repositories;

import com.eaproject.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepo extends JpaRepository<User, Integer> {
}
