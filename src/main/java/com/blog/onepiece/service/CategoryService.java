package com.blog.onepiece.service;

import com.blog.onepiece.entity.Category;
import com.blog.onepiece.exception.CategoryException;
import com.blog.onepiece.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Category get(int id) {
        Optional<Category> result = this.categoryRepository.findById(id);

        if (result.isEmpty()) {
            throw new CategoryException(CategoryException.NOT_FOUND);
        }

        return result.get();
    }

    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    public void delete(int id) {
        Category category = get(id);
        this.categoryRepository.delete(category);
    }
}
