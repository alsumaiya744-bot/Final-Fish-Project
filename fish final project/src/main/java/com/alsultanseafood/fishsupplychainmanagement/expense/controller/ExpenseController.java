package com.alsultanseafood.fishsupplychainmanagement.expense.controller;



import com.alsultanseafood.fishsupplychainmanagement.expense.entity.Expense;
import com.alsultanseafood.fishsupplychainmanagement.expense.service.ExpenseService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(
        origins =
                "http://127.0.0.1:5500")
public class ExpenseController {

    private final ExpenseService
            expenseService;

    public ExpenseController(
            ExpenseService expenseService) {

        this.expenseService =
                expenseService;
    }

    @PostMapping
    public Expense addExpense(
            @RequestBody
            Expense expense) {

        return expenseService
                .addExpense(
                        expense);
    }

    @GetMapping
    public List<Expense>
    getAllExpenses() {

        return expenseService
                .getAllExpenses();
    }

    @PutMapping(
            "/{expenseId}")
    public Expense updateExpense(
            @PathVariable
            Long expenseId,

            @RequestBody
            Expense expense) {

        return expenseService
                .updateExpense(
                        expenseId,
                        expense);
    }

    @DeleteMapping(
            "/{expenseId}")
    public void deleteExpense(
            @PathVariable
            Long expenseId) {

        expenseService
                .deleteExpense(
                        expenseId);
    }
}
