package com.blog.onepiece.service;

import com.blog.onepiece.entity.Category;
import com.blog.onepiece.entity.Post;
import com.blog.onepiece.entity.User;
import com.blog.onepiece.exception.CategoryException;
import com.blog.onepiece.exception.PostException;
import com.blog.onepiece.repository.CategoryRepository;
import com.blog.onepiece.repository.PostRepository;
import com.blog.onepiece.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    private PostRepository postRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository,
                       CategoryRepository categoryRepository,
                       UserRepository userRepository) {

        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAll() {
        return this.postRepository.findAll();
    }

    public Post get(int id) {
        Optional<Post> result = this.postRepository.findById(id);

        if (result.isEmpty()) {
            throw new PostException(PostException.NOT_FOUND);
        }

        return result.get();
    }

    public Post save(int categoryId, int userId, Post post) {
        Optional<Category> findCategory = this.categoryRepository.findById(categoryId);
        Optional<User> findUser = this.userRepository.findById(userId);

        if (findCategory.isEmpty() ||findUser.isEmpty()) {
            throw new CategoryException("category/user not found.");
        }

        Category category = findCategory.get();
        User user = findUser.get();

        post.setUser(user);
        post.setCategory(category);

        return this.postRepository.save(post);
    }

    public void delete(int id) {
        Post post = get(id);
        this.postRepository.delete(post);
    }
}
