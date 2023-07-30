package com.enigma.pos.service;

import com.enigma.pos.entity.Product;
import com.enigma.pos.model.request.ProductRequest;
import com.enigma.pos.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    List<ProductResponse> getAll();
    ProductResponse getByCode(String code);
    Product getProductByCode(String code);
    ProductResponse update(ProductRequest request);
    void deleteByCode(String code);
}
