package com.alsultanseafood.fishsupplychainmanagement.cart.dto;



public class AddToCartRequest {

    private Long userId;
    private Long fishId;
    private Double quantity;

    public AddToCartRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(
            Long userId) {
        this.userId = userId;
    }

    public Long getFishId() {
        return fishId;
    }

    public void setFishId(
            Long fishId) {
        this.fishId = fishId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(
            Double quantity) {
        this.quantity = quantity;
    }
}