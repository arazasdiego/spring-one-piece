package com.blog.onepiece.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryReadDto {

    private int id;
    private String description;
    private LocalDateTime lastUpdated;

    public CategoryReadDto() {
    }


    public CategoryReadDto(int id, String description, LocalDateTime lastUpdated) {
        this.id = id;
        this.description = description;
        this.lastUpdated = lastUpdated;
    }
}
