package com.alsultanseafood.fishsupplychainmanagement.expense.entity;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY)
    private Long expenseId;

    private LocalDate expenseDate;

    @Enumerated(EnumType.STRING)
private ExpenseType expenseType;
    private Double amount;

    private String description;

    public Expense() {
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(
            Long expenseId) {
        this.expenseId = expenseId;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(
            LocalDate expenseDate) {
        this.expenseDate = expenseDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(
            String description) {
        this.description = description;
    }
}