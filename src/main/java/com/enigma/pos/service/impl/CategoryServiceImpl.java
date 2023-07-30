package com.enigma.pos.service.impl;

import com.enigma.pos.entity.Category;
import com.enigma.pos.repository.CategoryRepository;
import com.enigma.pos.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getOrSave(String category) {
        return categoryRepository.findByCategoryIgnoreCase(category).orElseGet(() ->
            categoryRepository.saveAndFlush(Category.builder().category(category).build())
        );
    }
}
