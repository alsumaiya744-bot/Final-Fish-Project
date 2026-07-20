package com.alsultanseafood.fishsupplychainmanagement.userhome.dto;



import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;

import java.util.List;

public class UserHomeDto {

    private int totalOrders;

    private int pendingOrders;

    private int deliveredOrders;

    private List<Fish> fishes;

   

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(
            int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(
            int pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public int getDeliveredOrders() {
        return deliveredOrders;
    }

    public void setDeliveredOrders(
            int deliveredOrders) {
        this.deliveredOrders = deliveredOrders;
    }

    public List<Fish> getFishes() {
        return fishes;
    }

    public void setFishes(
            List<Fish> fishes) {
        this.fishes = fishes;
    }

    

    
}