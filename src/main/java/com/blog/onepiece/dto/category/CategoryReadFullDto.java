package com.blog.onepiece.dto.category;

import com.blog.onepiece.dto.post.PostReadDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CategoryReadFullDto {

    private int id;
    private String description;
    private LocalDateTime lastUpdated;

    private Set<PostReadDto> posts = new HashSet<>();

    public CategoryReadFullDto() {
    }

    public CategoryReadFullDto(int id, String description, LocalDateTime lastUpdated, Set<PostReadDto> posts) {
        this.id = id;
        this.description = description;
        this.lastUpdated = lastUpdated;
        this.posts = posts;
    }
}
