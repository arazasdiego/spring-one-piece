package com.blog.onepiece.controller;

import com.blog.onepiece.dto.category.CategoryCreateDto;
import com.blog.onepiece.dto.category.CategoryEditDto;
import com.blog.onepiece.dto.category.CategoryReadDto;
import com.blog.onepiece.dto.category.CategoryReadFullDto;
import com.blog.onepiece.dto.post.PostReadDto;
import com.blog.onepiece.entity.Category;
import com.blog.onepiece.entity.Post;
import com.blog.onepiece.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private ICategoryService categoryService;
    private ModelMapper mapper;

    @Autowired
    public CategoryController(ICategoryService categoryService, ModelMapper mapper) {
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @GetMapping("/categories")
    public List<CategoryReadDto> getAll() {
        var categories = this.categoryService.getAll();
        return categories.stream().map(this::convertToCategoryReadDto).collect(Collectors.toList());
    }

    @GetMapping("/categories/{id}")
    public CategoryReadFullDto get(@PathVariable int id) {
        Category category =  this.categoryService.get(id);
        return convertToCategoryReadFullDto(category);
    }

    @PostMapping("/categories")
    public CategoryReadDto create(@Valid @RequestBody CategoryCreateDto categoryCreateDto) {
        Category category = convertToCategory(categoryCreateDto);
        category.setId(0);

        return convertToCategoryReadDto(this.categoryService.save(category));
    }

    @PutMapping("/categories")
    public CategoryReadDto update(@Valid @RequestBody CategoryEditDto categoryEditDto) {
        Category category = convertToCategory(categoryEditDto);
        category.setLastUpdated(LocalDateTime.now());

        return convertToCategoryReadDto(this.categoryService.save(category));
    }

    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable int id) {
        this.categoryService.delete(id);
        return "category has been deleted";
    }

    private CategoryReadDto convertToCategoryReadDto(Category category) {
        CategoryReadDto categoryReadDto = this.mapper.map(category, CategoryReadDto.class);
        return categoryReadDto;
    }

    private CategoryReadFullDto convertToCategoryReadFullDto(Category category) {
        CategoryReadFullDto categoryReadFullDto = this.mapper.map(category, CategoryReadFullDto.class);
        categoryReadFullDto.setPosts(category.getPosts().stream().map(this::convertToPostReadDto).collect(Collectors.toSet()));

        return categoryReadFullDto;
    }

    private Category convertToCategory(CategoryCreateDto categoryCreateDto) {
        Category category = this.mapper.map(categoryCreateDto, Category.class);
        return category;
    }

    private Category convertToCategory(CategoryEditDto categoryEditDto) {
        Category category = this.mapper.map(categoryEditDto, Category.class);
        return category;
    }

    private PostReadDto convertToPostReadDto(Post post) {
        PostReadDto postReadDto = this.mapper.map(post, PostReadDto.class);
        return postReadDto;
    }
}
