package com.example.cartservice.repository;

import com.example.cartservice.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository {
    @Query(value = "select * from gio_hang where khach_hang_id = ?", nativeQuery = true)
    Cart findbyCustomerID(long khach_hang_id);
}
