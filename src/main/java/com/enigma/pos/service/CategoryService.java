package com.enigma.pos.service;

import com.enigma.pos.entity.Category;

public interface CategoryService {
    Category getOrSave(String category);
}
