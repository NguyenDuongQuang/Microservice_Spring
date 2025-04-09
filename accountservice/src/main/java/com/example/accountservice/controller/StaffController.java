package com.example.accountservice.controller;

import com.example.accountservice.repository.StaffRepository;
import com.example.accountservice.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
@CrossOrigin("*")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRepository staffRepository;
}
