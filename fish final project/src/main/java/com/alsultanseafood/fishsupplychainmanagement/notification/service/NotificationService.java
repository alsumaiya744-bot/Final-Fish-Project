package com.alsultanseafood.fishsupplychainmanagement.notification.service;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alsultanseafood.fishsupplychainmanagement.notification.entity.Notification;
import com.alsultanseafood.fishsupplychainmanagement.notification.repository.NotificationRepository;
import com.alsultanseafood.fishsupplychainmanagement.user.entity.User;
import com.alsultanseafood.fishsupplychainmanagement.user.repository.UserRepository;

@Service
public class NotificationService {

    private final NotificationRepository repository;
    private final UserRepository userRepository;

    public NotificationService(
            NotificationRepository repository,
            UserRepository userRepository) {

        this.repository =
                repository;

        this.userRepository =
                userRepository;
    }

    public void createNotification(
            Long userId,
            String message) {

        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow();

        Notification notification =
                new Notification();

        notification.setUser(user);
        notification.setMessage(message);
        notification.setIsRead(false);
        notification.setCreatedAt(
                LocalDateTime.now());

        repository.save(
                notification);
    }

    public List<Notification>
    getNotifications(
            Long userId) {

        return repository
                .findByUser_UserIdOrderByCreatedAtDesc(
                        userId);
    }
}
