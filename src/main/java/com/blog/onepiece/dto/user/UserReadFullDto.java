package com.blog.onepiece.dto.user;

import com.blog.onepiece.dto.post.PostReadDto;
import com.blog.onepiece.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserReadFullDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    private Set<PostReadDto> posts = new HashSet<>();

    public UserReadFullDto() {
    }

    public UserReadFullDto(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
