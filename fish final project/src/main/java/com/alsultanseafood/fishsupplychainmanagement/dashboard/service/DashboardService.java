package com.alsultanseafood.fishsupplychainmanagement.dashboard.service;



import com.alsultanseafood.fishsupplychainmanagement.dashboard.dto.ActivityDto;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.dto.DashboardDto;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.dto.ExpenseChartDto;
import java.util.List;

public interface DashboardService {

    DashboardDto getDashboard();

    List<ExpenseChartDto>
getExpenseChart();

List<ActivityDto> getActivities();
}