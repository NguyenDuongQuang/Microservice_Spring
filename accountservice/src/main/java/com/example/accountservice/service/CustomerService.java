package com.example.accountservice.service;

import com.example.accountservice.dto.CustomerDto;
import com.example.accountservice.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface CustomerService {
    List<Customer>getAll();

    ResponseEntity<?>AddCustomer(CustomerDto customerDto);

    ResponseEntity<Customer>UpdateCustomer(CustomerDto customerDto);

    ResponseEntity<List<Customer>>DeleteCustomer(Long id);

    List<Customer>searchAll(String search);

    List<Customer>searchByEmail(String email);

    List<Customer>searchByDate(String searchDate);
}
