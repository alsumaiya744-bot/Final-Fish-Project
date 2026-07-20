package com.alsultanseafood.fishsupplychainmanagement.dashboard.dto;



import com.alsultanseafood.fishsupplychainmanagement.expense.entity.ExpenseType;

public class ExpenseChartDto {

    private ExpenseType expenseType;
    private Double amount;

    public ExpenseChartDto() {
    }

    public ExpenseChartDto(
            ExpenseType expenseType,
            Double amount) {

        this.expenseType = expenseType;
        this.amount = amount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(
            ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(
            Double amount) {
        this.amount = amount;
    }
}