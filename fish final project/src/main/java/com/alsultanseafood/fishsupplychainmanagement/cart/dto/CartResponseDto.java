package com.alsultanseafood.fishsupplychainmanagement.cart.dto;


public class CartResponseDto {

    private Long cartId;
    private Long fishId;
    private String fishName;
    private Double sellingPrice;
    private Double quantity;
    private Double totalAmount;
    private String imagePath;

    public CartResponseDto() {
    }

    public CartResponseDto(
            Long cartId,
            Long fishId,
            String fishName,
            Double sellingPrice,
            Double quantity,
            Double totalAmount,
            String imagePath) {

        this.cartId = cartId;
        this.fishId = fishId;
        this.fishName = fishName;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.imagePath = imagePath;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getFishId() {
        return fishId;
    }

    public String getFishName() {
        return fishName;
    }

    public Double getSellingPrice() {
    return sellingPrice;
}

    public Double getQuantity() {
        return quantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public String getImagePath() {
        return imagePath;
    }
}
