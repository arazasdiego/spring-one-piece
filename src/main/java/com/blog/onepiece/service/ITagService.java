package com.blog.onepiece.service;

import com.blog.onepiece.entity.Tag;

import java.util.List;

public interface ITagService {

    List<Tag> geAll();

    Tag get(int id);

    Tag save(Tag tag);

    void delete(int id);
}
