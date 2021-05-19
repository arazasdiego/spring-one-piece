package com.blog.onepiece.controller;

import com.blog.onepiece.dto.tag.TagCreateDto;
import com.blog.onepiece.dto.tag.TagEditDto;
import com.blog.onepiece.dto.tag.TagReadDto;
import com.blog.onepiece.entity.Tag;
import com.blog.onepiece.service.ITagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {

    private ITagService tagService;
    private ModelMapper mapper;

    @Autowired
    public TagController(ITagService tagService, ModelMapper mapper) {
        this.tagService = tagService;
        this.mapper = mapper;
    }

    @GetMapping("/tags")
    public List<Tag> getAll() {
        return this.tagService.geAll();
    }

    @GetMapping("/tags/{id}")
    public Tag get(@PathVariable int id) {
        return this.tagService.get(id);
    }

    @PostMapping("/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public TagReadDto create(@Valid @RequestBody TagCreateDto tagCreateDto) {
        Tag tag = convertToTag(tagCreateDto);
        tag.setId(0);

        return convertToTagReadDto(this.tagService.save(tag));
    }

    @PutMapping("/tags")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TagReadDto create(@Valid @RequestBody TagEditDto tagEditDto) {
        Tag tag = convertToTag(tagEditDto);
        tag.setLastUpdated(LocalDateTime.now());

        return convertToTagReadDto(this.tagService.save(tag));
    }

    private TagReadDto convertToTagReadDto(Tag tag) {
        TagReadDto tagReadDto = this.mapper.map(tag, TagReadDto.class);
        return tagReadDto;
    }

    private Tag convertToTag(TagCreateDto tagCreateDto) {
        Tag tag = this.mapper.map(tagCreateDto, Tag.class);
        return tag;
    }

    private Tag convertToTag(TagEditDto tagEditDto) {
        Tag tag = this.mapper.map(tagEditDto, Tag.class);
        return tag;
    }
}
