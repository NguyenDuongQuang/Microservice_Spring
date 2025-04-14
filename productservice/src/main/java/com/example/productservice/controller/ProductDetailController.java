package com.example.productservice.controller;

import com.example.productservice.entity.Product_Detail;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.repository.Product_DetailRepository;
import com.example.productservice.service.ProductService;
import com.example.productservice.service.Product_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product_detail")
@CrossOrigin("*")
public class ProductDetailController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private Product_DetailRepository productDetailRepository;

    @Autowired
    private Product_DetailService product_detailService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Product_Detail>> getAllProductDetails() {
        List<Product_Detail> list = productDetailRepository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/edit/{id}")
    public Product_Detail editProductDetail(@PathVariable Long id) {
        return productDetailRepository.findByID(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Product_Detail> updateProductDetail(@RequestBody Product_Detail productDetail) {
        return product_detailService.editProductSetaiD(productDetail);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Product_Detail>> deleteProductDetail(Long id) {
        return product_detailService.deleteProductDetails(id);
    }

    @GetMapping("/search/{search}")
    public List<Product_Detail> searchProductDetail(@PathVariable String search) {
        return product_detailService.searchAllProductDetails(search);
    }

    @PutMapping("/updateSLSPCT")
    public ResponseEntity<Product_Detail> updateSLSPCT(@RequestBody Product_Detail productDetail) {
        return product_detailService.editSLProductDetail(productDetail);
    }
}
