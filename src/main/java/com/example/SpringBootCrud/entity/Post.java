package com.example.SpringBootCrud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String postTitle;
    @Column(name = "content",length = 10000)
    private String content;
    private String image;
    private Date addDate;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;
}
