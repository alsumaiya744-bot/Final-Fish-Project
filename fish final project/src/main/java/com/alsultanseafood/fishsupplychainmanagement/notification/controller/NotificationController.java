package com.alsultanseafood.fishsupplychainmanagement.notification.controller;


import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.alsultanseafood.fishsupplychainmanagement.notification.entity.Notification;
import com.alsultanseafood.fishsupplychainmanagement.notification.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(
            NotificationService service) {

        this.service =
                service;
    }

    @GetMapping("/{userId}")
    public List<Notification>
    getNotifications(
            @PathVariable
            Long userId) {

        return service
                .getNotifications(
                        userId);
    }
}