package com.example.accountservice.controller;

import com.example.accountservice.dto.CustomerDto;
import com.example.accountservice.entity.Customer;
import com.example.accountservice.repository.CustomerRepository;
import com.example.accountservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/get-all")
    public List<Customer> getAll() {
        return customerService.getAll();
    }
    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.AddCustomer(customerDto);
    }
    @GetMapping("/edit/{id}")
    public Customer editCustomer(@PathVariable("id") Long id) {
        return customerRepository.findByID(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.UpdateCustomer(customerDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Customer>>deleteCustemer(@RequestBody CustomerDto customerDto) {
        Long id=customerDto.getId();
        return customerService.DeleteCustomer(id);
    }
    @GetMapping("/search/{search}")
    public List<Customer> searchAll(@PathVariable("search") String search) {
        return customerRepository.findByAll(search);
    }
}
