package com.example.productservice.service.Impl;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import com.example.productservice.entity.Product_Detail;
import com.example.productservice.entity.Product_Image;
import com.example.productservice.message.Message;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.repository.Product_DetailRepository;
import com.example.productservice.repository.Product_ImageRepository;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Product_ImageRepository productImageRepository;

    @Autowired
    private Product_DetailRepository productDetailRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    Long id_product;
    private boolean isValid(String str) {
        return str.matches("^[a-zA-Z\\d\\s\\S]+$");
    }


    @Override
    public List<Product> findByAllProduct() {

        List<Product> products = productRepository.findAllProduct();
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        for(Product p : products){
            ProductDto productDto = new ProductDto();
            productDto.setId(p.getId());
            productDto.setGia(p.getGia());
            productDto.setTenSanPham(p.getTenSanPham());
            productDto.setTrangThai(p.getTrangThai());

            String image = productImageRepository.getTenAnhSanPham_HienThiDanhSach(p.getId());
            productDto.setProduct_image(image);
            productDtos.add(productDto);

        }
        return products;
    }

    @Override
    public ResponseEntity<?> addProduct(ProductDto productDto) {
        String errorMessage;
        Message errorResponse;

        Product product = productRepository.checkLap(productDto.getTenSanPham());
        if(product != null){
            errorMessage="Trùng Tên Sản Phẩm";
            errorResponse =new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(productDto==null || !isValid(productDto.getTenSanPham())){
            errorMessage="Vui Lòng Nhập Tên Sản Phẩm";
            errorResponse =new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Category category=categoryRepository.findByID(productDto.getCategory_id());
        Product p=new Product();
        p.setId(productDto.getId());
        p.setGia(productDto.getGia());
        p.setTenSanPham(productDto.getTenSanPham());
        p.setIsDeleted(false);
        p.setTrangThai(0);
        p.setCategory(category);
        productRepository.save(p);

        id_product = p.getId();

        Product_Detail product_detail=new Product_Detail();
        ArrayList<Product_Detail> product_details=new ArrayList<>();
        product_detail.setProduct(p);
        product_detail.setTrangThai(true);
        product_detail.setSoLuong(productDto.getSoLuong());
        product_detail.setSoLuongTam(productDto.getSoLuong());
        product_details.add(product_detail);

        Map<String,Object>response=new HashMap<>();
        response.put("list",product_details);
        response.put("id_product",p.getId());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Product> updateProduct(ProductDto productDto) {
        String errorMessage;
        Message errorResponse;
        Product product = productRepository.checkLap(productDto.getTenSanPham());
        if(product != null){
            errorMessage="Trùng Tên Sản Phẩm";
            errorResponse =new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(productDto==null || !isValid(productDto.getTenSanPham())){
            errorMessage="Vui Lòng Nhập Tên Sản Phẩm";
            errorResponse =new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(productDto.getGia()<=0){
            errorMessage="Giá Tiền Sản Phẩm Phải Lớn Hơn 0";
            errorResponse =new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Product> pro=productRepository.findById(productDto.getId());
            if(pro.isPresent()){
                Product p=pro.get();
                p.setGia(productDto.getGia());
                p.setTrangThai(0);
                p.setTenSanPham(productDto.getTenSanPham());
                p.setCategory(productDto.getCategory());
                productRepository.save(p);
                return ResponseEntity.ok(p);
            }
            else{
                return ResponseEntity.badRequest().build();
            }

        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Product>> deleteProduct(Long id) {
        try {
            Optional<Product> product =productRepository.findById(id);
            if(product.isPresent()){
                Product p=product.get();
                p.setIsDeleted(true);
                p.setTrangThai(0);
                productRepository.save(p);
                List<Product> products=findByAllProduct();
                return ResponseEntity.ok(products);

            }else{
                return ResponseEntity.badRequest().build();
            }
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> searchProductAll(String search) {
        List<Product> list =productRepository.findByAll(search);
        ArrayList<ProductDto> products=new ArrayList<>();
        for(Product p:list){
            ProductDto productDto=new ProductDto();
            productDto.setId(p.getId());
            productDto.setTenSanPham(p.getTenSanPham());
            productDto.setCategory(p.getCategory());
            productDto.setTrangThai(p.getTrangThai());
            productDto.setGia(p.getGia());
            productDto.setProduct_id(p.getId());

            String image=productImageRepository.getTenAnhSanPham_HienThiDanhSach(p.getId());
            productDto.setImage_product(image);
            products.add(productDto);
        }
        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<?> getProductPrice(Float gia1, Float gia2) {
       try {
           List<Product> list=productRepository.findByGia(gia1,gia2);
           ArrayList<ProductDto> products=new ArrayList<>();
           for(Product p:list){
               ProductDto productDto=new ProductDto();
               productDto.setId(p.getId());
               productDto.setTenSanPham(p.getTenSanPham());
               productDto.setCategory(p.getCategory());
               productDto.setGia(p.getGia());
               String image=productImageRepository.getTenAnhSanPham_HienThiDanhSach(p.getId());
               productDto.setImage_product(image);
               products.add(productDto);
           }
           return ResponseEntity.ok(products);
       } catch (Exception e) {
           return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
       }
    }

    @Override
    public List<Object> productDetail(long id_Product) {
        Product product=productRepository.findByID(id_Product);
        List<Product_Detail> productDetails=productDetailRepository.findByProductID(id_Product);
        List<Object> respone=new ArrayList<>();
        respone.add(product);
        respone.add(productDetails);
        return respone;
    }
}
