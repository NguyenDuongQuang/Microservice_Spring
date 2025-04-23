package com.example.authservice.client;

import com.example.authservice.dto.StaffResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "accountservice")
public interface StaffClientDto {
    @GetMapping("/staff/email/{email}")
    StaffResponse findByEmail(@PathVariable("email") String email);
}
