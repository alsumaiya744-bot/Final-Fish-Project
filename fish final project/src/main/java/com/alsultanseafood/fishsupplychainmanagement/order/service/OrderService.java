package com.alsultanseafood.fishsupplychainmanagement.order.service;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.entity.Activity;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import com.alsultanseafood.fishsupplychainmanagement.cart.entity.Cart;
import com.alsultanseafood.fishsupplychainmanagement.cart.repository.CartRepository;
import com.alsultanseafood.fishsupplychainmanagement.order.dto.OrderRequest;
import com.alsultanseafood.fishsupplychainmanagement.order.entity.Order;
import com.alsultanseafood.fishsupplychainmanagement.order.entity.OrderItem;
import com.alsultanseafood.fishsupplychainmanagement.order.repository.OrderRepository;
import com.alsultanseafood.fishsupplychainmanagement.user.entity.User;
import com.alsultanseafood.fishsupplychainmanagement.user.repository.UserRepository;
import com.alsultanseafood.fishsupplychainmanagement.notification.service.NotificationService;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final NotificationService notificationService;
    private final ActivityRepository activityRepository;

    public OrderService(
            OrderRepository orderRepository,
            UserRepository userRepository,
            CartRepository cartRepository,
        NotificationService notificationService, ActivityRepository activityRepository) {

        this.orderRepository =
                orderRepository;
        this.userRepository =
                userRepository;
        this.cartRepository =
                cartRepository;
                this.notificationService =
            notificationService;
            this.activityRepository =
        activityRepository;
    }

    public Order placeOrder(
            OrderRequest request) {

        User user =
                userRepository
                        .findById(
                                request.getUserId())
                        .orElseThrow();

        List<Cart> cartItems =
                cartRepository.findByUserUserId(
        user.getUserId());
        Order order =
                new Order();

        order.setUser(user);
        order.setPaymentMethod(
                request.getPaymentMethod());

        order.setPaymentStatus(
                request.getPaymentStatus());

        order.setDeliveryAddress(
                request.getDeliveryAddress());

        order.setPaymentId(
                request.getPaymentId());

        order.setOrderStatus(
                "PROCESSING");

        order.setOrderDate(
                LocalDateTime.now());

        double totalAmount = 0;
        double totalQuantity = 0;

        List<OrderItem> items =
                new ArrayList<>();

        for (Cart cart : cartItems) {

            OrderItem item =
                    new OrderItem();

            item.setOrder(order);

            item.setFish(
                    cart.getFish());

            item.setQuantity(
                    cart.getQuantity());

            


                    

            item.setTotal(
                    cart.getTotalAmount());

            items.add(item);

            totalAmount +=
                    cart.getTotalAmount();

            totalQuantity +=
                    cart.getQuantity();
        }

      order.setTotalAmount(
        request.getTotalAmount());

        order.setTotalQuantity(
                totalQuantity);

        order.setOrderItems(
                items);

        Order savedOrder =
                orderRepository
                        .save(order);
                        notificationService
        .createNotification(
                user.getUserId(),
                "Your order has been placed successfully.");

        cartRepository.deleteAll(
                cartItems);

        return savedOrder;
    }

    public List<Order>
    getUserOrders(
            Long userId) {

        return orderRepository
                .findByUser_UserId(
                        userId);
    }

    public List<Order>
    getAllOrders() {

        return orderRepository
                .findAll();
    }




    public void updateStatus(
        Long orderId,
        String status) {

    Order order =
            orderRepository
                    .findById(orderId)
                    .orElseThrow(
                            () ->
                                    new RuntimeException(
                                            "Order Not Found"));

    String currentStatus =
            order.getOrderStatus();

    if (
            currentStatus.equals(
                    "CANCELLED")
            ||
            currentStatus.equals(
                    "DELIVERED")
    ) {
        throw new RuntimeException(
                "Status cannot be changed");
    }

    order.setOrderStatus(
            status);

    orderRepository.save(
            order);
            if(status.equals(
        "DELIVERED")) {

    activityRepository.save(
            new Activity(
                    "Order Delivered",
                    LocalDateTime.now()
            )
    );
}

            if(status.equals("ACCEPTED")){

    notificationService
            .createNotification(
                    order.getUser()
                            .getUserId(),
                    "Your order has been accepted.");
}
else if(status.equals("PACKED")){

    notificationService
            .createNotification(
                    order.getUser()
                            .getUserId(),
                    "Your order has been packed.");
}
else if(status.equals(
        "OUT_FOR_DELIVERY")){

    notificationService
            .createNotification(
                    order.getUser()
                            .getUserId(),
                    "Your order is out for delivery.");
}
else if(status.equals(
        "DELIVERED")){

    notificationService
            .createNotification(
                    order.getUser()
                            .getUserId(),
                    "Your order has been delivered.");
}
else if(status.equals(
        "CANCELLED")){

    notificationService
            .createNotification(
                    order.getUser()
                            .getUserId(),
                    "Your order has been cancelled.");
}
}









public void cancelOrder(
        Long orderId){

    Order order =
            orderRepository
                    .findById(orderId)
                    .orElseThrow();

    if(
            !order.getOrderStatus()
                    .equals(
                            "PROCESSING"
                    )
    ){
        throw new RuntimeException(
                "Cannot Cancel Order");
    }

    order.setOrderStatus(
            "CANCELLED"
    );

    orderRepository.save(
            order
    );
}
}
