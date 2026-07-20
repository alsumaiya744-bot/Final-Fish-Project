package com.alsultanseafood.fishsupplychainmanagement.notification.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alsultanseafood.fishsupplychainmanagement.notification.entity.Notification;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification>
    findByUser_UserIdOrderByCreatedAtDesc(
            Long userId);
}