package com.blog.onepiece.service;

import com.blog.onepiece.entity.User;
import com.blog.onepiece.exception.UserException;
import com.blog.onepiece.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User get(int id) {
        Optional<User> result = this.userRepository.findById(id);

        if (result.isEmpty()) {
            throw new UserException(UserException.NOT_FOUND);
        }

        return result.get();
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public void delete(int id) {
        User user = get(id);
        this.userRepository.delete(user);
    }
}
