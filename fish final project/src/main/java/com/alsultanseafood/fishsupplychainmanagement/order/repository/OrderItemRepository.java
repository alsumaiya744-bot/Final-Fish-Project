package com.alsultanseafood.fishsupplychainmanagement.order.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.alsultanseafood.fishsupplychainmanagement.order.entity.OrderItem;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {

}
