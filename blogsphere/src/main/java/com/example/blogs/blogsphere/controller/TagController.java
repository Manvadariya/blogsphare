package com.example.blogs.blogsphere.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogs.blogsphere.entity.Tag;
import com.example.blogs.blogsphere.service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;
    
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    // GET /api/tags - Retrieve all tags.
    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    // GET /api/tags/{id} - Retrieve a tag by ID.
    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tagService.getTagById(id));
    }

    // POST /api/tags - Create a new tag.
    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag created = tagService.createTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/tags/{id} - Update an existing tag.
    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") Long id, @RequestBody Tag tag) {
        return ResponseEntity.ok(tagService.updateTag(id, tag));
    }

    // DELETE /api/tags/{id} - Delete a tag.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable("id") Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
