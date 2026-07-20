package com.alsultanseafood.fishsupplychainmanagement.order.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alsultanseafood.fishsupplychainmanagement.order.dto.OrderRequest;
import com.alsultanseafood.fishsupplychainmanagement.order.entity.Order;
import com.alsultanseafood.fishsupplychainmanagement.order.service.OrderService;

@RestController
@RequestMapping("/api/orders")

public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService) {
        this.orderService =
                orderService;
    }

    @PostMapping
    public ResponseEntity<Order>
    placeOrder(
            @RequestBody
            OrderRequest request) {

        return ResponseEntity.ok(
                orderService
                        .placeOrder(
                                request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>>
    getUserOrders(
            @PathVariable
            Long userId) {

        return ResponseEntity.ok(
                orderService
                        .getUserOrders(
                                userId));
    }

    @GetMapping
    public ResponseEntity<List<Order>>
    getAllOrders() {

        return ResponseEntity.ok(
                orderService
                        .getAllOrders());
    }




    @PutMapping("/{orderId}/status")
public ResponseEntity<String>
updateStatus(
        @PathVariable
        Long orderId,

        @RequestParam
        String status) {

    orderService.updateStatus(
            orderId,
            status);

    return ResponseEntity.ok(
            "Status Updated");
}










@PutMapping("/cancel/{orderId}")
public ResponseEntity<String>
cancelOrder(
        @PathVariable
        Long orderId){

    orderService
            .cancelOrder(
                    orderId
            );

    return ResponseEntity.ok(
            "Order Cancelled"
    );
}
}