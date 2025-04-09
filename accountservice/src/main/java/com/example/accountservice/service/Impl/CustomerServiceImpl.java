package com.example.accountservice.service.Impl;

import com.example.accountservice.dto.CustomerDto;
import com.example.accountservice.entity.Customer;
import com.example.accountservice.message.Message;
import com.example.accountservice.repository.CustomerRepository;
import com.example.accountservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAllCustomer();
    }

    @Override
    public ResponseEntity<?> AddCustomer(CustomerDto customerDto) {
        String errorMessage;
        Message errorResponse;

        Optional<Customer> checkMail= Optional.ofNullable(customerRepository.findByEmail(customerDto.getEmail()));
        if(checkMail.isPresent()){
            errorMessage = "Trùng Email";
            errorResponse= new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(customerDto.getDiaChi()==null ||customerDto.getMatKhau() ==null|| customerDto.getSoDienThoai()==null||customerDto.getEmail()==null || customerDto.getNgaySinh()==null){
            errorMessage="Nhập Đầy Đủ Thông Tin";
            errorResponse = new Message(errorMessage,TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        if(customerDto.getSoDienThoai().length()!=10){
            errorMessage = "Số Điẹne Thoại Phải Là 10 Số";
            errorResponse = new Message(errorMessage,TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        //Email
        String email = customerDto.getEmail();
        String emailRegex = "^[A-Za-z0-9+_.-]+@.+";//kiểm tra định dạng email
        Pattern pattern = Pattern.compile(emailRegex);//tạo Pattern để kiểm tra email
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            errorMessage = "Địa chỉ Eamil không đúng định dạng";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Date currentDate = new Date();
        Date dateOfBirth = customerDto.getNgaySinh();

        if (dateOfBirth.after(currentDate)) {
            errorMessage = "Ngày Sinh Không ở Tương Lai";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            Customer customer = new Customer();
            customer.setDiaChi(customerDto.getDiaChi());
            customer.setMatKhau(customerDto.getMatKhau());
            customer.setSoDienThoai(customerDto.getSoDienThoai());
            customer.setHoTen(customerDto.getHoTen());
            customer.setIsDeleted(false);
            customer.setNgaySinh(customerDto.getNgaySinh());
            customer.setEmail(customerDto.getEmail());
            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Customer> UpdateCustomer(CustomerDto customerDto) {
        String errorMessage;
        Message errorResponse;

        Optional<Customer> checkMail= Optional.ofNullable(customerRepository.findByEmail(customerDto.getEmail()));
        if(checkMail.isPresent()){
            errorMessage = "Trùng Email";
            errorResponse= new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(customerDto.getDiaChi()==null ||customerDto.getMatKhau() ==null|| customerDto.getSoDienThoai()==null||customerDto.getEmail()==null || customerDto.getNgaySinh()==null){
            errorMessage="Nhập Đầy Đủ Thông Tin";
            errorResponse = new Message(errorMessage,TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        if(customerDto.getSoDienThoai().length()!=10){
            errorMessage = "Số Điẹne Thoại Phải Là 10 Số";
            errorResponse = new Message(errorMessage,TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        //Email
        String email = customerDto.getEmail();
        String emailRegex = "^[A-Za-z0-9+_.-]+@.+";//kiểm tra định dạng email
        Pattern pattern = Pattern.compile(emailRegex);//tạo Pattern để kiểm tra email
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            errorMessage = "Địa chỉ Eamil không đúng định dạng";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Date currentDate = new Date();
        Date dateOfBirth = customerDto.getNgaySinh();

        if (dateOfBirth.after(currentDate)) {
            errorMessage = "Ngày Sinh Không ở Tương Lai";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Customer> optionalCus= customerRepository.findById(customerDto.getId());
            if(optionalCus.isPresent()){
                Customer customer = optionalCus.get();
                customer.setDiaChi(customerDto.getDiaChi());
                customer.setMatKhau(customerDto.getMatKhau());
                customer.setHoTen(customerDto.getHoTen());
                customer.setSoDienThoai(customerDto.getSoDienThoai());
                customer.setNgaySinh(customerDto.getNgaySinh());
                customer.setIsDeleted(false);
                customer.setEmail(customerDto.getEmail());
                customerRepository.save(customer);
                return ResponseEntity.ok(customer);
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Customer>> DeleteCustomer(Long id) {
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
            if(optionalCustomer.isPresent()){
                Customer customer = optionalCustomer.get();
                customer.setIsDeleted(true);
                customerRepository.save(customer);

                List<Customer> cusList= customerRepository.findAllCustomer();
                return ResponseEntity.ok(cusList);
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Customer> searchAll(String search) {
        List<Customer>list= customerRepository.findByAll(search);
        return list;
    }

    @Override
    public List<Customer> searchByEmail(String email) {
        return List.of();
    }

    @Override
    public List<Customer> searchByDate(String searchDate) {
        return List.of();
    }
}
