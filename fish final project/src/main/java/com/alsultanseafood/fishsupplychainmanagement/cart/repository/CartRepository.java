package com.alsultanseafood.fishsupplychainmanagement.cart.repository;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alsultanseafood.fishsupplychainmanagement.cart.entity.Cart;

public interface CartRepository
        extends JpaRepository<Cart, Long> {

    List<Cart> findByUserUserId(
            Long userId);
}