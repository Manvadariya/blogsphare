package com.example.blogs.blogsphere.service;

import com.example.blogs.blogsphere.DAO.TagDAO;
import com.example.blogs.blogsphere.entity.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {

    private final TagDAO tagDAO;

    public TagService(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    // Retrieve all tags
    public List<Tag> getAllTags() {
        return tagDAO.findAll();
    }

    // Retrieve a tag by its ID
    public Tag getTagById(Long tagId) {
        return tagDAO.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + tagId));
    }

    // Create a new tag
    @Transactional
    public Tag createTag(Tag tag) {
        return tagDAO.save(tag);
    }

    // Update an existing tag
    @Transactional
    public Tag updateTag(Long tagId, Tag updatedTag) {
        Tag existingTag = getTagById(tagId);
        existingTag.setName(updatedTag.getName());
        return tagDAO.save(existingTag);
    }

    // Delete a tag by its ID
    @Transactional
    public void deleteTag(Long tagId) {
        tagDAO.deleteById(tagId);
    }
}
