package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public List<Product> getAllProduct(){
        return productService.findByAllProduct();
    }

    @GetMapping("/product_detail")
    public List<Object> productDetail(@RequestParam long id_Product) {
        return productService.productDetail(id_Product);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable("id") long id) {
        return productService.deleteProduct(id);
    }
}
