package com.example.accountservice.service.Impl;

import com.example.accountservice.dto.StaffDto;
import com.example.accountservice.entity.Staff;
import com.example.accountservice.message.Message;
import com.example.accountservice.repository.StaffRepository;
import com.example.accountservice.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> findCategoryAll() {
        return staffRepository.findAllStaff();
    }

    @Override
    public ResponseEntity<?> addCategory(StaffDto staffDto) {
        String errorMessage;
        Message errorRespone;

        Optional<Staff> checkEmail = Optional.ofNullable(staffRepository.findByEmail(staffDto.getEmail()));
        if (checkEmail.isPresent()){
            errorMessage = "Trùng email nhân viên";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }

        if (staffDto.getHoTen() == null ||
                staffDto.getDiaChi() == null || staffDto.getSoDienThoai() == null ||
                staffDto.getNgaySinh() == null || staffDto.getEmail() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }

        //SDT
        if (staffDto.getSoDienThoai().length() != 10) {
            errorMessage = "SDT phải đủ 10 số";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }
        //email
        String email = staffDto.getEmail();
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            errorMessage = "Mail không đúng định dạng";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }

        //dateOfbirth
        Date dateC = new Date();
        Date dateOfBirth = staffDto.getNgaySinh();
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(birthDate, currentDate).getYears();

        if(age <= 16){
            errorMessage = "Nhân Viên Phải Từ 18 Tuổi Trở Lên";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }


        if (dateOfBirth.after(dateC)) {
            errorMessage = "Ngày Sinh Không Được Quá Ngày Hiện Tại";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }
        try {
            Staff staff = new Staff();
            staff.setHoTen(staffDto.getHoTen());
            staff.setGioiTinh(staffDto.isGioiTinh());
            staff.setEmail(staffDto.getEmail());
            staff.setDiaChi(staffDto.getDiaChi());
            staff.setNgaySinh(staffDto.getNgaySinh());
            staff.setMatKhau(staffDto.getMatKhau());
            staff.setSoDienThoai(staffDto.getSoDienThoai());
            staff.setIsDeleted(false);
            staffRepository.save(staff);
            return ResponseEntity.ok(staff);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Staff> updateCategory(StaffDto staffDto) {
        String errorMessage;
        Message errorRespone;

        if (staffDto.getHoTen() == null ||
                staffDto.getDiaChi() == null || staffDto.getSoDienThoai() == null ||
                staffDto.getNgaySinh() == null || staffDto.getEmail() == null
        ) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }

        //SDT
        if (staffDto.getSoDienThoai().length() != 10) {
            errorMessage = "Số điện thoại phải đủ 10 số";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }
        //email
        String email = staffDto.getEmail();
        //dinh dang email
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        //partern de ktra email
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            errorMessage = "Mail is not in the correct format";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }


        //dateOfbirth
        Date dateOfBirth = staffDto.getNgaySinh();
        Date dateC = new Date();
        if (dateOfBirth.after(dateC)) {
            errorMessage = "Ngày sinh không được vượt quá thời gian hiện tại";
            errorRespone = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorRespone, HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Staff> optionalStaff = staffRepository.findById(staffDto.getId());
            if (optionalStaff.isPresent()) {
                Staff staff = optionalStaff.get();
                staff.setHoTen(staffDto.getHoTen());
                staff.setEmail(staffDto.getEmail());
                staff.setDiaChi(staffDto.getDiaChi());
                staff.setNgaySinh(staffDto.getNgaySinh());
                staff.setSoDienThoai(staffDto.getSoDienThoai());
                staff.setGioiTinh(staffDto.isGioiTinh());
                staff.setTrangThai(staffDto.getStatus());
                staffRepository.save(staff);
                return ResponseEntity.ok(staff);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Staff>> deleteCategory(Long id) {
        try {
            Optional<Staff> optionalStaff=staffRepository.findById(id);
            if(optionalStaff.isPresent()){
                Staff staff=optionalStaff.get();
                staff.setIsDeleted(true);
                staffRepository.save(staff);
                List<Staff> staffList=findCategoryAll();
                return ResponseEntity.ok(staffList);
            }else{
                return ResponseEntity.badRequest().build();
            }

        }catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Staff> searchByEmail(String email) {
        Staff staffList=staffRepository.findByEmail(email);
        return (List<Staff>) staffList;
    }

    @Override
    public List<Staff> searchAll(String search) {
        List<Staff> staffList=staffRepository.findByAll(search);
        return staffList;
    }
}
