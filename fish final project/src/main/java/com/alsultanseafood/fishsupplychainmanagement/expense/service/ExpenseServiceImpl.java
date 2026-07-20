package com.alsultanseafood.fishsupplychainmanagement.expense.service;



import com.alsultanseafood.fishsupplychainmanagement.expense.entity.Expense;
import com.alsultanseafood.fishsupplychainmanagement.expense.repository.ExpenseRepository;

import com.alsultanseafood.fishsupplychainmanagement.dashboard.entity.Activity;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.repository.ActivityRepository;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl
        implements ExpenseService {

    private final ExpenseRepository
            expenseRepository;
            private final ActivityRepository activityRepository;

    public ExpenseServiceImpl(
            ExpenseRepository expenseRepository,ActivityRepository activityRepository) {

        this.expenseRepository =
                expenseRepository;
                  this.activityRepository =
            activityRepository;
    }

    @Override
public Expense addExpense(
        Expense expense) {

    Expense savedExpense =
            expenseRepository
                    .save(expense);

    activityRepository.save(
            new Activity(
                    "Expense Added",
                    LocalDateTime.now()
            )
    );

    return savedExpense;
}

    @Override
    public List<Expense>
    getAllExpenses() {

        return expenseRepository
                .findAll();
    }

    @Override
    public Expense updateExpense(
            Long expenseId,
            Expense expense) {

        Expense existingExpense =
                expenseRepository
                        .findById(
                                expenseId)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Expense not found"));

        existingExpense.setExpenseDate(
                expense.getExpenseDate());

        existingExpense.setExpenseType(
                expense.getExpenseType());

        existingExpense.setAmount(
                expense.getAmount());

        existingExpense.setDescription(
                expense.getDescription());

        return expenseRepository
                .save(
                        existingExpense);
    }

    @Override
    public void deleteExpense(
            Long expenseId) {

        expenseRepository
                .deleteById(
                        expenseId);
    }
}