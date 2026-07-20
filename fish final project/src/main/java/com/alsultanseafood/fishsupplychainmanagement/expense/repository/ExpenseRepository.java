package com.alsultanseafood.fishsupplychainmanagement.expense.repository;


import com.alsultanseafood.fishsupplychainmanagement.dashboard.dto.ExpenseChartDto;
import com.alsultanseafood.fishsupplychainmanagement.expense.entity.Expense;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository
        extends JpaRepository<
                Expense,
                Long> {

                    List<Expense>
findByExpenseDateBetween(
        LocalDate startDate,
        LocalDate endDate);



        @Query(
        "SELECT SUM(e.amount) FROM Expense e")
Double sumExpense();

@Query(
        "SELECT new " +
        "com.alsultanseafood." +
        "fishsupplychainmanagement." +
        "dashboard.dto." +
        "ExpenseChartDto(" +
        "e.expenseType," +
        "SUM(e.amount)) " +
        "FROM Expense e " +
        "GROUP BY e.expenseType")
List<ExpenseChartDto>
getExpenseChart();
}
