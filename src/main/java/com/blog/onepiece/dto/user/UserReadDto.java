package com.blog.onepiece.dto.user;

import lombok.Data;

@Data
public class UserReadDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
}
