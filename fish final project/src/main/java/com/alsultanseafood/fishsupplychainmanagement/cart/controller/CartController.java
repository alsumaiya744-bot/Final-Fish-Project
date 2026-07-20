package com.alsultanseafood.fishsupplychainmanagement.cart.controller;




import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.alsultanseafood.fishsupplychainmanagement.cart.dto.AddToCartRequest;
import com.alsultanseafood.fishsupplychainmanagement.cart.dto.CartResponseDto;
import com.alsultanseafood.fishsupplychainmanagement.cart.service.CartService;
import com.alsultanseafood.fishsupplychainmanagement.cart.dto.UpdateCartQuantityRequest;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CartController {

    private final CartService cartService;

    public CartController(
            CartService cartService) {

        this.cartService =
                cartService;
    }

    @PostMapping
    public void addToCart(
            @RequestBody
            AddToCartRequest request) {

        cartService.addToCart(
                request);
    }

    @GetMapping("/{userId}")
    public List<CartResponseDto>
    getCartItems(
            @PathVariable
            Long userId) {

        return cartService
                .getCartItems(
                        userId);
    }

    @DeleteMapping("/{cartId}")
    public void removeItem(
            @PathVariable
            Long cartId) {

        cartService
                .removeItem(
                        cartId);
    }

    @PutMapping("/{cartId}")
public void updateQuantity(
        @PathVariable
        Long cartId,

        @RequestBody
        UpdateCartQuantityRequest request) {

    cartService.updateQuantity(
            cartId,
            request.getQuantity());
}
}