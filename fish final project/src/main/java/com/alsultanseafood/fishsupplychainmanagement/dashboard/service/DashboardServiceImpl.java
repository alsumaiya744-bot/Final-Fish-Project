package com.alsultanseafood.fishsupplychainmanagement.dashboard.service;



import com.alsultanseafood.fishsupplychainmanagement.dashboard.dto.ActivityDto;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.dto.DashboardDto;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.dto.ExpenseChartDto;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.entity.Activity;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.repository.ActivityRepository;
import com.alsultanseafood.fishsupplychainmanagement.expense.repository.ExpenseRepository;
import com.alsultanseafood.fishsupplychainmanagement.order.repository.OrderRepository;
import com.alsultanseafood.fishsupplychainmanagement.procurement.repository.ProcurementRepository;
import com.alsultanseafood.fishsupplychainmanagement.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl
        implements DashboardService {

    private final ProcurementRepository
            procurementRepository;

    private final ExpenseRepository
            expenseRepository;

    private final OrderRepository
            orderRepository;

    private final UserRepository
            userRepository;
private final ActivityRepository activityRepository;
    public DashboardServiceImpl(
            ProcurementRepository procurementRepository,
            ExpenseRepository expenseRepository,
            OrderRepository orderRepository,
            UserRepository userRepository,ActivityRepository activityRepository) {

        this.procurementRepository =
                procurementRepository;

        this.expenseRepository =
                expenseRepository;

        this.orderRepository =
                orderRepository;

        this.userRepository =
                userRepository;

                this.activityRepository =
        activityRepository;
    }

    @Override
    public DashboardDto getDashboard() {

        DashboardDto dto =
                new DashboardDto();

        Double fishPurchased =
                procurementRepository
                        .sumPurchaseQuantity();

        Double procurementCost =
                procurementRepository
                        .sumTotalAmount();

        Double expenses =
                expenseRepository
                        .sumExpense();

        Double sales =
                orderRepository
                        .sumSales();

        if (fishPurchased == null) {
            fishPurchased = 0.0;
        }

        if (procurementCost == null) {
            procurementCost = 0.0;
        }

        if (expenses == null) {
            expenses = 0.0;
        }

        if (sales == null) {
            sales = 0.0;
        }

        Double profit =
                sales -
                procurementCost -
                expenses;

        dto.setTotalFishPurchased(
                fishPurchased);

        dto.setTotalExpense(
                expenses);

        dto.setTotalSales(
                sales);

        dto.setNetProfit(
                profit);

        dto.setTotalOrders(
                orderRepository.count());

        dto.setTotalCustomers(
                userRepository
                        .countByRole(
                                "USER"));

        return dto;
    }

    @Override
public List<ExpenseChartDto>
getExpenseChart() {

    return expenseRepository
            .getExpenseChart();
}



@Override
public List<ActivityDto>
getActivities() {

    List<Activity> activities =
            activityRepository
                    .findTop10ByOrderByDateDesc();

    List<ActivityDto> dtoList =
            new ArrayList<>();

    for (Activity activity : activities) {

        dtoList.add(
                new ActivityDto(
                        activity.getActivity(),
                        activity.getDate()
                                .toLocalDate()
                                .toString()
                )
        );
    }

    return dtoList;
}
}
