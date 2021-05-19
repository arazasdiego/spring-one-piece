package com.blog.onepiece.controller;

import com.blog.onepiece.dto.post.PostCreateDto;
import com.blog.onepiece.dto.post.PostEditDto;
import com.blog.onepiece.dto.post.PostReadDto;
import com.blog.onepiece.entity.Post;
import com.blog.onepiece.exception.PostException;
import com.blog.onepiece.service.IPostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private IPostService postService;
    private ModelMapper mapper;

    @Autowired
    public PostController(IPostService postService, ModelMapper mapper) {
        this.postService = postService;
        this.mapper = mapper;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return this.postService.getAll();
    }

    @GetMapping("/posts/{id}")
    public Post get(@PathVariable int id) {
        return this.postService.get(id);
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostReadDto create(@Valid @RequestBody PostCreateDto postCreateDto) {
        Post newPost = convertToPost(postCreateDto);
        newPost.setId(0);

        return convertToPostReadDto(this.postService.save(postCreateDto.getCategoryId(), postCreateDto.getUserId(), newPost));
    }

    @PutMapping("/posts")
    public PostReadDto update(@Valid @RequestBody PostEditDto postEditDto) {
        if (this.postService.get(postEditDto.getId()) == null) {
            throw new PostException(PostException.NOT_FOUND);
        }
        Post newPost = convertToPost(postEditDto);

        return convertToPostReadDto(this.postService.save(postEditDto.getCategoryId(), postEditDto.getUserId(), newPost));
    }

    @DeleteMapping("/posts/{id}")
    public String delete(@PathVariable int id) {
        this.postService.delete(id);
        return "post has been deleted";
    }

    private PostReadDto convertToPostReadDto(Post post) {
        PostReadDto postReadDto = this.mapper.map(post, PostReadDto.class);
        postReadDto.setAuthorName(post.getUser().getAuthorName());

        return postReadDto;
    }

    private Post convertToPost(PostCreateDto postCreateDto) {
        Post post = new Post();

        post.setTitle(postCreateDto.getTitle());
        post.setBody(postCreateDto.getBody());
        return post;
    }

    private Post convertToPost(PostEditDto postEditDto) {
        Post post = new Post();

        post.setId(postEditDto.getId());
        post.setTitle(postEditDto.getTitle());
        post.setBody(postEditDto.getBody());
        post.setLastUpdated(LocalDateTime.now());

        return post;
    }
}
