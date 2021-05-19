package com.blog.onepiece.controller;

import com.blog.onepiece.dto.post.PostReadDto;
import com.blog.onepiece.dto.user.UserCreateDto;
import com.blog.onepiece.dto.user.UserEditDto;
import com.blog.onepiece.dto.user.UserReadDto;
import com.blog.onepiece.dto.user.UserReadFullDto;
import com.blog.onepiece.entity.Post;
import com.blog.onepiece.entity.User;
import com.blog.onepiece.exception.UserException;
import com.blog.onepiece.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    private IUserService userService;
    private ModelMapper mapper;

    @Autowired
    public UserController(IUserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/users")
    public List<UserReadDto> getAll() {
        List<User> users = this.userService.getAll();
        return users
                .stream()
                .map(this::convertToUserReadDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UserReadFullDto get(@PathVariable int id) {
        User user = this.userService.get(id);
        return convertToUserReadFullDto(user);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@Valid @RequestBody UserCreateDto userCreateDto) {
        User user = convertToUser(userCreateDto);
        user.setId(0);

        return convertToUserReadDto(this.userService.save(user));
    }

    @PutMapping("/users")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserReadDto update(@Valid @RequestBody UserEditDto userEditDto) {
        if (this.userService.get(userEditDto.getId()) == null) {
            throw new UserException("user was not found");
        }
        User user = convertToUser(userEditDto);
        user.setLastUpdated(LocalDateTime.now());

        return convertToUserReadDto(this.userService.save(user));
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable int id) {
        this.userService.delete(id);
        return "user has now deleted.";
    }

    private User convertToUser(UserCreateDto userCreateDto) {
        User user = this.mapper.map(userCreateDto, User.class);
        return user;
    }

    private User convertToUser(UserEditDto userEditDto) {
        User user = this.mapper.map(userEditDto, User.class);
        return user;
    }

    private UserReadDto convertToUserReadDto(User user) {
        UserReadDto userReadDto = this.mapper.map(user, UserReadDto.class);
        return  userReadDto;
    }

    private UserReadFullDto convertToUserReadFullDto(User user) {
        UserReadFullDto userReadFullDto = this.mapper.map(user, UserReadFullDto.class);
        Set<PostReadDto> postReadDtos = user.getPosts().stream().map(this::convertToPostReadDto).collect(Collectors.toSet());

        userReadFullDto.setPosts(postReadDtos);
        return userReadFullDto;
    }

    private PostReadDto convertToPostReadDto(Post post) {
        PostReadDto postReadDto = this.mapper.map(post, PostReadDto.class);
        return postReadDto;
    }
}
