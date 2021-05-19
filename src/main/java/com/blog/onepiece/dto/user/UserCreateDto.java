package com.blog.onepiece.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserCreateDto {

    @NotEmpty(message = "first name is required")
    private String firstName;

    @NotEmpty(message = "last name is required")
    private String lastName;

    @NotEmpty(message = "email is required")
    private String email;
}
