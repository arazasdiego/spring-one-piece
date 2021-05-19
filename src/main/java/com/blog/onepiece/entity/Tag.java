package com.blog.onepiece.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts = new ArrayList<>();

    public Tag() {
    }

    public Tag(String name, List<Post> posts) {
        this.name = name;
        this.posts = posts;
    }
}
