package com.example.productservice.service.Impl;

import com.example.productservice.entity.Category;
import com.example.productservice.message.Message;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findByCategoryAll();
    }

    private boolean isValid(String str) {
        return str.matches("^[a-zA-Z\\d\\s\\S]+$");
    }


    @Override
    public ResponseEntity<?> addCategory(Category category) {
        String errorMessage;
        Message errorResponse;
        Category cate = categoryRepository.findByName(category.getLoaiSanPham());
        if (cate != null) {
            errorMessage = "Trùng Loại Sản Phẩm";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (category.getLoaiSanPham() == null || !isValid(category.getLoaiSanPham())) {
            errorMessage = "Vui Lòng Nhập Tên Loại sản Phẩm";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Category c = new Category();
            c.setLoaiSanPham(category.getLoaiSanPham());
            c.setIsDeleted(false);
            categoryRepository.save(c);
            return ResponseEntity.ok(c);
        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Category> updateCategory(Category category) {
        String errorMessage;
        Message errorResponse;
        Category cate = categoryRepository.findByName(category.getLoaiSanPham());
        if (cate != null) {
            errorMessage = "Trùng Loại Sản Phẩm";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if (!isValid(category.getLoaiSanPham()) || category.getLoaiSanPham() == null) {
            errorMessage = "Vui Lòng Nhập Loại Sản Phẩm";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Category> c = categoryRepository.findById(category.getId());
            if (c.isPresent()) {
                Category catego = c.get();
                catego.setLoaiSanPham(category.getLoaiSanPham());
                categoryRepository.save(catego);
                return ResponseEntity.ok(catego);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Category>> deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        try {
            if (category.isPresent()) {
                Category c = category.get();
                c.setIsDeleted(true);
                categoryRepository.save(c);
                List<Category> categories = findAllCategory();
                return ResponseEntity.ok(categories);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }
}
