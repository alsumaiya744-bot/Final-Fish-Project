package com.alsultanseafood.fishsupplychainmanagement.expense.service;



import com.alsultanseafood.fishsupplychainmanagement.expense.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense addExpense(
            Expense expense);

    List<Expense> getAllExpenses();

    Expense updateExpense(
            Long expenseId,
            Expense expense);

    void deleteExpense(
            Long expenseId);
}
