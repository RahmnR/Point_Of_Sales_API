package com.enigma.pos.service;

import com.enigma.pos.entity.Product;
import com.enigma.pos.model.response.ProductResponse;

import java.awt.event.PaintEvent;
import java.util.List;

public interface ProductService {
    ProductResponse create(Product product);
    List<ProductResponse> getAll();
    ProductResponse getByCode(String code);
    ProductResponse update(Product product);
    void deleteByCode(String code);
}
