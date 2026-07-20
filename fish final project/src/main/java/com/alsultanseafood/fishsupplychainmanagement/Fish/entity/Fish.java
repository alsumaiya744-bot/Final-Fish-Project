package com.alsultanseafood.fishsupplychainmanagement.Fish.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "fish")
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fishId;

    private String fishName;

    private String category;

    private Double purchasePrice;

private Double sellingPrice;

    private Double availableStock;

    private String storageType;

    private String imagePath;

    private Boolean active = true;




    

    public Fish() {
    }






    public Fish(String fishName, String category, Double availableStock, String storageType,
            String imagePath, Boolean active) {
        this.fishName = fishName;
        this.category = category;
        
        this.availableStock = availableStock;
        this.storageType = storageType;
        this.imagePath = imagePath;
        this.active = active;
    }






    public Long getFishId() {
        return fishId;
    }






    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }






    public String getFishName() {
        return fishName;
    }






    public void setFishName(String fishName) {
        this.fishName = fishName;
    }






    public String getCategory() {
        return category;
    }






    public void setCategory(String category) {
        this.category = category;
    }






   public Double getPurchasePrice() {
    return purchasePrice;
}

public void setPurchasePrice(Double purchasePrice) {
    this.purchasePrice = purchasePrice;
}

public Double getSellingPrice() {
    return sellingPrice;
}

public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
}





    public Double getAvailableStock() {
        return availableStock;
    }






    public void setAvailableStock(Double availableStock) {
        this.availableStock = availableStock;
    }






    public String getStorageType() {
        return storageType;
    }






    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }






    public String getImagePath() {
        return imagePath;
    }






    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }






    public Boolean getActive() {
        return active;
    }






    public void setActive(Boolean active) {
        this.active = active;
    }




    
    
}