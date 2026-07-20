package com.alsultanseafood.fishsupplychainmanagement.cart.service;





import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;
import com.alsultanseafood.fishsupplychainmanagement.Fish.repository.FishRepository;
import com.alsultanseafood.fishsupplychainmanagement.cart.dto.AddToCartRequest;
import com.alsultanseafood.fishsupplychainmanagement.cart.dto.CartResponseDto;
import com.alsultanseafood.fishsupplychainmanagement.cart.entity.Cart;
import com.alsultanseafood.fishsupplychainmanagement.cart.repository.CartRepository;
import com.alsultanseafood.fishsupplychainmanagement.user.entity.User;
import com.alsultanseafood.fishsupplychainmanagement.user.repository.UserRepository;

@Service
public class CartServiceImpl
        implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final FishRepository fishRepository;

    public CartServiceImpl(
            CartRepository cartRepository,
            UserRepository userRepository,
            FishRepository fishRepository) {

        this.cartRepository =
                cartRepository;

        this.userRepository =
                userRepository;

        this.fishRepository =
                fishRepository;
    }

    @Override
    public void addToCart(
            AddToCartRequest request) {

        User user =
                userRepository.findById(
                        request.getUserId())
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "User Not Found"));

        Fish fish =
                fishRepository.findById(
                        request.getFishId())
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Fish Not Found"));

        Cart cart =
                new Cart();

        cart.setUser(user);
        cart.setFish(fish);
        cart.setQuantity(
                request.getQuantity());

        cart.setTotalAmount(
        request.getQuantity()
                * fish.getSellingPrice());

        cartRepository.save(cart);
    }

    @Override
    public List<CartResponseDto>
    getCartItems(
            Long userId) {

        List<Cart> carts =
                cartRepository
                        .findByUserUserId(
                                userId);

        return carts.stream()
                .map(cart ->
                        new CartResponseDto(
                                cart.getCartId(),
                                cart.getFish().getFishId(),
                                cart.getFish().getFishName(),
                                cart.getFish().getSellingPrice(),
                                cart.getQuantity(),
                                cart.getTotalAmount(),
                                cart.getFish().getImagePath()))
                .collect(Collectors.toList());
    }

    @Override
    public void removeItem(
            Long cartId) {

        cartRepository
                .deleteById(cartId);
    }




    @Override
public void updateQuantity(
        Long cartId,
        Double quantity) {

    Cart cart =
            cartRepository.findById(
                    cartId)
                    .orElseThrow(
                            () ->
                                    new RuntimeException(
                                            "Cart Item Not Found"));

    cart.setQuantity(
            quantity);

    cart.setTotalAmount(
        quantity *
        cart.getFish()
            .getSellingPrice());

    cartRepository.save(
            cart);
}
}