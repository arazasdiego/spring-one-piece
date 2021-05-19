package com.blog.onepiece.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostReadDto {

    private int id;
    private String title;
    private String body;
    private String categoryName;
    private String authorName;
    private LocalDateTime lastUpdated;

    public PostReadDto() {
    }

    public PostReadDto(int id, String title, String body, String categoryName, String authorName, LocalDateTime lastUpdated) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.categoryName = categoryName;
        this.authorName = authorName;
        this.lastUpdated = lastUpdated;
    }
}
