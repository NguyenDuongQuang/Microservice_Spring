package com.example.productservice.controller;

import com.example.productservice.entity.Category;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get-all")
    public List<Category>getAll(){
        return categoryService.findAllCategory();
    }

    @PostMapping("/add")
    public ResponseEntity<?>addCate(@RequestBody Category category){
       return categoryService.addCategory(category);
    }

    @GetMapping("/edit/{id}")
    public Category edit(@PathVariable("id") Long id){
        return categoryRepository.findByID(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Category>updateCate(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Category>> deleteCate(@PathVariable("id") Long id){
        return categoryService.deleteCategory(id);
    }
}
