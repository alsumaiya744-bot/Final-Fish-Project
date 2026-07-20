package com.alsultanseafood.fishsupplychainmanagement.cart.entity;






import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;
import com.alsultanseafood.fishsupplychainmanagement.user.entity.User;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fish_id")
    private Fish fish;

    private Double quantity;

    private Double totalAmount;

    public Cart() {
    }

    public Cart(
            User user,
            Fish fish,
            Double quantity,
            Double totalAmount) {

        this.user = user;
        this.fish = fish;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(
            Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(
            Double totalAmount) {
        this.totalAmount =
                totalAmount;
    }
}