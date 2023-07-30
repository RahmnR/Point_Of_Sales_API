package com.enigma.pos.service.impl;

import com.enigma.pos.entity.Category;
import com.enigma.pos.entity.Product;
import com.enigma.pos.model.request.ProductRequest;
import com.enigma.pos.model.response.ProductResponse;
import com.enigma.pos.repository.ProductRepository;
import com.enigma.pos.service.CategoryService;
import com.enigma.pos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ProductResponse create(ProductRequest request) {
        try {
            Category category = categoryService.getOrSave(request.getCategory());
            Product product = Product.builder()
                    .codeProduct(codeGenerate(request))
                    .name(request.getProductName())
                    .price(request.getPrice())
                    .category(category)
                    .build();
            productRepository.saveAndFlush(product);
            return ProductResponse.builder()
                    .codeProduct(product.getCodeProduct())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .category(category.getCategory())
                    .build();
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ProductResponse> getAll() {
        List<Product> allProduct = productRepository.findAllProduct();
        return allProduct.stream().map(product -> {
            return ProductResponse.builder()
                    .codeProduct(product.getCodeProduct())
                    .productName(product.getName())
                    .price(product.getPrice())
                    .category(product.getCategory().getCategory())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public ProductResponse getByCode(String code) {
        Product product = getProductByCode(code);
        return ProductResponse.builder()
                .codeProduct(product.getCodeProduct())
                .productName(product.getName())
                .price(product.getPrice())
                .category(product.getCategory().getCategory())
                .build();
    }

    @Override
    public Product getProductByCode(String code) {
        return productRepository.findProductByCode(code).orElseThrow();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ProductResponse update(ProductRequest request) {
        try {
            String idProduct = productRepository.findProductByCode(request.getCodeProduct()).orElseThrow().getId();
            Category category = categoryService.getOrSave(request.getCategory());
            productRepository.updateProduct(request.getProductName(), request.getPrice(), category, idProduct);

            return ProductResponse.builder()
                    .codeProduct(request.getCodeProduct())
                    .productName(request.getProductName())
                    .price(request.getPrice())
                    .category(category.getCategory())
                    .build();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteByCode(String code) {
        try {
            Product product = productRepository.findProductByCode(code).orElseThrow();
            productRepository.deleteProductByCode(code);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private String codeGenerate(ProductRequest request){
        int size = productRepository.findAllProduct().size();
        String productName = request.getProductName();
        return productName.substring(0,3)+(size+1);
    }
}
