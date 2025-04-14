package com.example.productservice.service.Impl;

import com.example.productservice.entity.Product;
import com.example.productservice.entity.Product_Detail;
import com.example.productservice.message.Message;
import com.example.productservice.repository.Product_DetailRepository;
import com.example.productservice.service.Product_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class Product_DetailServiceImpl implements Product_DetailService {

    @Autowired
    private Product_DetailRepository productDetailRepository;

    @Override
    public ResponseEntity<Product_Detail> editProductSetaiD(Product_Detail productDetail) {
        String errorMessage;
        Message errorReponse;

        if(productDetail.getSoLuong()<0){
            errorMessage = "Số Lượng Không Được Nhỏ Hơn 0";
            errorReponse=new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorReponse, HttpStatus.BAD_REQUEST);
        }
        try {

            Optional<Product_Detail> optionalPro=productDetailRepository.findById(productDetail.getId());
            if(optionalPro.isPresent()){
                Product_Detail pro=optionalPro.get();
                if(productDetail.getSoLuong()<=0){
                    pro.setTrangThai(false);
                }
                else {
                    pro.setTrangThai(true);
                }
                pro.setSoLuong(productDetail.getSoLuong());
                pro.setSoLuongTam(productDetail.getSoLuongTam());
                Product product=productDetail.getProduct();
                if(product!=null){
                    pro.setProduct(product);
                }
                if(product.getGia()<0){
                    errorMessage="Giá Phải Lớn Hơn 0";
                    errorReponse=new Message(errorMessage, TrayIcon.MessageType.ERROR);
                    return new ResponseEntity(errorReponse, HttpStatus.BAD_REQUEST);
                }
                productDetailRepository.save(pro);
            }else{
                return ResponseEntity.badRequest().build();
            }

        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @Override
    public ResponseEntity<List<Product_Detail>> deleteProductDetails(Long id) {
        try{

            Optional<Product_Detail> productDetail=productDetailRepository.findById(id);
            if(productDetail.isPresent()){
                Product_Detail pro=productDetail.get();
                pro.setIsDeleted(true);
                productDetailRepository.save(pro);
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Product_Detail> searchAllProductDetails(String search) {
        List<Product_Detail> list = productDetailRepository.findByAll(search);
        return list;
    }

    @Override
    public ResponseEntity<Product_Detail> editSLProductDetail(Product_Detail productDetail) {
        Product_Detail pro=productDetailRepository.findByID(productDetail.getId());
        pro.setSoLuong(productDetail.getSoLuong());
        pro.setSoLuongTam(productDetail.getSoLuongTam());
        productDetailRepository.save(pro);
        return ResponseEntity.ok(pro);
    }
}
