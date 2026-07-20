package com.alsultanseafood.fishsupplychainmanagement.userhome.service;



import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;
import com.alsultanseafood.fishsupplychainmanagement.Fish.repository.FishRepository;
import com.alsultanseafood.fishsupplychainmanagement.notification.entity.Notification;
import com.alsultanseafood.fishsupplychainmanagement.notification.repository.NotificationRepository;
import com.alsultanseafood.fishsupplychainmanagement.order.repository.OrderRepository;
import com.alsultanseafood.fishsupplychainmanagement.userhome.dto.NotificationDto;
import com.alsultanseafood.fishsupplychainmanagement.userhome.dto.UserHomeDto;
import com.alsultanseafood.fishsupplychainmanagement.order.entity.Order;

import com.alsultanseafood.fishsupplychainmanagement.order.repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserHomeServiceImpl
        implements UserHomeService {

    private final FishRepository fishRepository;
    private final OrderRepository orderRepository;
    

    public UserHomeServiceImpl(
            FishRepository fishRepository, OrderRepository orderRepository, NotificationRepository notificationRepository) {

        this.fishRepository =
                fishRepository;
                this.orderRepository =
            orderRepository;
            
    }
    

    @Override
    public UserHomeDto getHomeData(
        Long userId) {

        UserHomeDto dto =
                new UserHomeDto();

        List<Fish> fishes =
                fishRepository.findAll();

        dto.setFishes(fishes);
         List<Order> orders =
        orderRepository
                .findByUser_UserId(
                        userId);

                        dto.setTotalOrders(
        orders.size());

dto.setPendingOrders(
        (int) orders.stream()
                .filter(
                        order ->
                                !order.getOrderStatus()
                                        .equals(
                                                "DELIVERED")
                                &&
                                !order.getOrderStatus()
                                        .equals(
                                                "CANCELLED")
                )
                .count());

dto.setDeliveredOrders(
        (int) orders.stream()
                .filter(
                        order ->
                                order.getOrderStatus()
                                        .equals(
                                                "DELIVERED")
                )
                .count());

       

        
       

        return dto;
    }


   

                        
}