package com.blog.onepiece.dto.user;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserEditDto {

    @NotNull
    private int id;

    @NotEmpty(message = "first name is required")
    private String firstName;

    @NotEmpty(message = "last name is required")
    private String lastName;

    @NotEmpty(message = "email is required")
    private String email;
}
