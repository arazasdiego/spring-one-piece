package com.blog.onepiece.service;

import com.blog.onepiece.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getAll();

    User get(int id);

    User save(User user);

    void delete(int id);
}
