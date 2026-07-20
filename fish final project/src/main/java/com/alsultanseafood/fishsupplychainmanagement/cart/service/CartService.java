package com.alsultanseafood.fishsupplychainmanagement.cart.service;




import java.util.List;

import com.alsultanseafood.fishsupplychainmanagement.cart.dto.AddToCartRequest;
import com.alsultanseafood.fishsupplychainmanagement.cart.dto.CartResponseDto;

public interface CartService {

    void addToCart(
            AddToCartRequest request);

    List<CartResponseDto> getCartItems(
            Long userId);

    void removeItem(
            Long cartId);

            void updateQuantity(
        Long cartId,
        Double quantity);
}