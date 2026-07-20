package com.alsultanseafood.fishsupplychainmanagement.dashboard.controller;


import com.alsultanseafood.fishsupplychainmanagement.dashboard.dto.DashboardDto;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.dto.ExpenseChartDto;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.service.DashboardService;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.dto.ActivityDto;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(
        origins =
        "http://127.0.0.1:5500")
public class DashboardController {

    private final DashboardService
            dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService =
                dashboardService;
    }

    @GetMapping
    public DashboardDto getDashboard() {

        return dashboardService
                .getDashboard();
    }


    @GetMapping("/expense-chart")
public List<ExpenseChartDto>
getExpenseChart() {

    return dashboardService
            .getExpenseChart();
}

@GetMapping("/activities")
public List<ActivityDto>
getActivities() {

    return dashboardService
            .getActivities();
}
}
