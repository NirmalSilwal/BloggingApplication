package com.eaproject.blog.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(name = "post_title", length = 100, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    private String imageName;

    private Date addedDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
