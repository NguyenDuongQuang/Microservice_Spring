package com.example.productservice.service;

import com.example.productservice.entity.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> findAllCategory();

    ResponseEntity<?>addCategory(Category category);

    ResponseEntity<Category>updateCategory(Category category);

    ResponseEntity<List<Category>>deleteCategory(Long id);
}
