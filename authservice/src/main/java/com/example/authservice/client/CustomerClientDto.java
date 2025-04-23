package com.example.authservice.client;

import com.example.authservice.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "accountservice", url = "${account.service.url}")
public interface CustomerClientDto {
    @GetMapping("/customer/email/{email}")
    CustomerResponse findByEmail(@PathVariable("email") String email);

    @GetMapping("/customers/{id}")
    CustomerResponse findByID(@PathVariable("id") Long id);
}
