package com.blog.onepiece.service;

import com.blog.onepiece.entity.Tag;
import com.blog.onepiece.exception.TagException;
import com.blog.onepiece.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements ITagService {

    private TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> geAll() {
        return this.tagRepository.findAll();
    }

    public Tag get(int id) {
        Optional<Tag> result = this.tagRepository.findById(id);

        if (result.isEmpty()) {
            throw new TagException(TagException.NOT_FOUND);
        }

        return result.get();
    }

    public Tag save(Tag tag) {
        return this.tagRepository.save(tag);
    }

    public void delete(int id) {
        Tag tag = get(id);
        this.tagRepository.delete(tag);
    }
}
