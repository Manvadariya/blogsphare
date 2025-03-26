package com.example.blogs.blogsphere.controller;

import com.example.blogs.blogsphere.entity.Tag;
import com.example.blogs.blogsphere.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;
    
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }
    
    // GET /api/tags - retrieve all tags
    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
    
    // GET /api/tags/{id} - retrieve a tag by id
    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }
    
    // POST /api/tags - create a new tag
    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }
    
    // PUT /api/tags/{id} - update an existing tag
    @PutMapping("/{id}")
    public Tag updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        return tagService.updateTag(id, tag);
    }
    
    // DELETE /api/tags/{id} - delete a tag
    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }
}
