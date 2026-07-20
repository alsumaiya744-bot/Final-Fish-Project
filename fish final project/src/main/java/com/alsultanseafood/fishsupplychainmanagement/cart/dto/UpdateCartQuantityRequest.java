package com.alsultanseafood.fishsupplychainmanagement.cart.dto;



public class UpdateCartQuantityRequest {

    private Double quantity;

    public UpdateCartQuantityRequest() {
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(
            Double quantity) {
        this.quantity = quantity;
    }
}
