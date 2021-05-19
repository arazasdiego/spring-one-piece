package com.blog.onepiece.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PostEditDto {

    @NotNull
    private int id;

    @NotNull
    private int categoryId;

    @NotNull
    private int userId;

    @NotEmpty(message = "title is required")
    private String title;

    @NotEmpty(message = "body is required")
    private String body;
}
