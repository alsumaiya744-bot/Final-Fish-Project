package com.alsultanseafood.fishsupplychainmanagement.report.dto;


public class ReportDto {

    private Double totalSales;

    private Double totalProcurement;

    private Double totalExpenses;

    private Double profit;

    public ReportDto() {
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(
            Double totalSales) {
        this.totalSales = totalSales;
    }

    public Double getTotalProcurement() {
        return totalProcurement;
    }

    public void setTotalProcurement(
            Double totalProcurement) {
        this.totalProcurement =
                totalProcurement;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(
            Double totalExpenses) {
        this.totalExpenses =
                totalExpenses;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(
            Double profit) {
        this.profit = profit;
    }
}