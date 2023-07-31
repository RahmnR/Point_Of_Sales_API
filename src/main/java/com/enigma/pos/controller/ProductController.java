package com.enigma.pos.controller;

import com.enigma.pos.model.request.ProductRequest;
import com.enigma.pos.model.response.ProductResponse;
import com.enigma.pos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest request){
        return productService.create(request);
    }

    @GetMapping
    public List<ProductResponse> ViewAll(@RequestParam(name = "code", required = false)String code){
        if (code==null) return productService.getAll();
        return List.of(productService.getByCode(code));
    }
    @PutMapping
    public ProductResponse update(@RequestBody ProductRequest request){
        return productService.update(request);
    }
    @DeleteMapping("/{code}")
    public String delete(@PathVariable String code){
        try{
            productService.deleteByCode(code);
            return "Success";
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Data Not Found");
        }
    }
}
