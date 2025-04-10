package com.example.accountservice.service;

import com.example.accountservice.dto.StaffDto;
import com.example.accountservice.entity.Staff;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {

    List<Staff>findCategoryAll();

    ResponseEntity<?>addCategory(StaffDto staffDto);

    ResponseEntity<Staff>updateCategory(StaffDto staffDto);

    ResponseEntity<List<Staff>>deleteCategory(Long id);

    List<Staff>searchByEmail(String email);

    List<Staff>searchAll(String search);
}
