package com.alsultanseafood.fishsupplychainmanagement.procurement.entity;



import jakarta.persistence.*;

import java.time.LocalDate;

import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;






@Entity
@Table(name = "procurement")
public class Procurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long procurementId;

    private LocalDate procurementDate;

    private String supplierName;

    private String supplierPhone;

    private Double purchaseQuantity;
    private Double purchasePrice;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fish_id")
    private Fish fish;
    

    public Procurement() {
    }

    public Long getProcurementId() {
        return procurementId;
    }

    public void setProcurementId(Long procurementId) {
        this.procurementId = procurementId;
    }

    public LocalDate getProcurementDate() {
        return procurementDate;
    }

    public void setProcurementDate(LocalDate procurementDate) {
        this.procurementDate = procurementDate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public Double getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Double purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }


    public Double getPurchasePrice() {
    return purchasePrice;
}

public void setPurchasePrice(
        Double purchasePrice) {
    this.purchasePrice = purchasePrice;
}
}