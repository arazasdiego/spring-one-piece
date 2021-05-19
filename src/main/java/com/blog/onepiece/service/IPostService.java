package com.blog.onepiece.service;

import com.blog.onepiece.entity.Post;

import java.util.List;

public interface IPostService {

    List<Post> getAll();

    Post get(int id);

    Post save(int categoryId, int userId, Post post);

    void delete(int id);
}
