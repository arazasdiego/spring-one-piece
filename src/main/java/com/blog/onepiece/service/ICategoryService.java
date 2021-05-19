package com.blog.onepiece.service;

import com.blog.onepiece.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAll();

    Category get(int id);

    Category save(Category category);

    void delete(int id);
}
