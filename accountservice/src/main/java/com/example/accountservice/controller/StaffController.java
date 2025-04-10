package com.example.accountservice.controller;

import com.example.accountservice.dto.StaffDto;
import com.example.accountservice.entity.Staff;
import com.example.accountservice.repository.StaffRepository;
import com.example.accountservice.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@CrossOrigin("*")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("get-all")
    public List<Staff> getAll() {
        return staffService.findCategoryAll();
    }

    @GetMapping("search/{search}")
    public List<Staff> getAllSearch(@PathVariable("search") String search) {
        return staffService.searchAll(search);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Staff>> delete(@RequestBody StaffDto staff) {
        Long id =staff.getId();
        return staffService.deleteCategory(id);
    }

    @GetMapping("/edit/{id}")
    public Staff editStaff(@PathVariable("id") Long id) {
        return staffRepository.findByID(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createStaff(@RequestBody StaffDto staff) {
        return staffService.addCategory(staff);
    }

    @PutMapping("/update")
    public ResponseEntity<Staff> updateStaff(@RequestBody StaffDto staff) {
        return staffService.updateCategory(staff);
    }
}
