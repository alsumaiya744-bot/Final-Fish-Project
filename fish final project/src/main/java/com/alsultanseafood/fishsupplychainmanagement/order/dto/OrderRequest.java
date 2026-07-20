package com.alsultanseafood.fishsupplychainmanagement.order.dto;


public class OrderRequest {

    private Long userId;
    private String paymentMethod;
    private String paymentStatus;
    private String deliveryAddress;
    private String paymentId;
    private Double totalAmount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(
            Long userId) {
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(
            String paymentMethod) {
        this.paymentMethod =
                paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(
            String paymentStatus) {
        this.paymentStatus =
                paymentStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(
            String deliveryAddress) {
        this.deliveryAddress =
                deliveryAddress;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(
            String paymentId) {
        this.paymentId = paymentId;
    }


    public Double getTotalAmount() {
    return totalAmount;
}

public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
}
}
