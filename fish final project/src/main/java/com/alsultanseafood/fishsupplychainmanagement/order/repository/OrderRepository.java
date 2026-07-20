package com.alsultanseafood.fishsupplychainmanagement.order.repository;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alsultanseafood.fishsupplychainmanagement.order.entity.Order;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order>
    findByUser_UserId(
            Long userId);

            List<Order>
findByOrderDateBetween(
        LocalDateTime startDate,
        LocalDateTime endDate);



        @Query(
        "SELECT SUM(o.totalAmount) " +
        "FROM Order o " +
        "WHERE o.orderStatus = 'DELIVERED'")
Double sumSales();
}
