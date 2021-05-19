package com.blog.onepiece.dto.post;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostCreateDto {

    @NotEmpty(message = "title is required")
    private String title;

    @NotEmpty(message = "body is required")
    private String body;

    @NotNull
    private int categoryId;

    @NotNull
    private int userId;

    public PostCreateDto() {
    }

    public PostCreateDto(String title, String body, int categoryId, int userId) {
        this.title = title;
        this.body = body;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
