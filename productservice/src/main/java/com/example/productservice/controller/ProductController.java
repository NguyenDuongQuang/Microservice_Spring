package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

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

    @GetMapping("/edit/{id}")
    public  Product editProduct(@PathVariable("id") Long id){
        return productRepository.findByID(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Product>updateProduct(@RequestBody ProductDto productDto)
    {
        return productService.updateProduct(productDto);
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<?> searchProduct(@PathVariable("search") String search){
        return productService.searchProductAll(search);
    }

   @GetMapping("/search/gia")
    public ResponseEntity<?>getPrice(@RequestParam float gia1, @RequestParam float gia2){
        return productService.getProductPrice(gia1, gia2);
   }
}
