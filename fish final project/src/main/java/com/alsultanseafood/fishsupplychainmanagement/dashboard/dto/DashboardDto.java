package com.alsultanseafood.fishsupplychainmanagement.dashboard.dto;



public class DashboardDto {

    private Double totalFishPurchased;
    private Double totalExpense;
    private Double totalSales;
    private Double netProfit;
    private Long totalOrders;
    private Long totalCustomers;

    public Double getTotalFishPurchased() {
        return totalFishPurchased;
    }

    public void setTotalFishPurchased(
            Double totalFishPurchased) {
        this.totalFishPurchased =
                totalFishPurchased;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(
            Double totalExpense) {
        this.totalExpense =
                totalExpense;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(
            Double totalSales) {
        this.totalSales =
                totalSales;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(
            Double netProfit) {
        this.netProfit =
                netProfit;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(
            Long totalOrders) {
        this.totalOrders =
                totalOrders;
    }

    public Long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(
            Long totalCustomers) {
        this.totalCustomers =
                totalCustomers;
    }
}