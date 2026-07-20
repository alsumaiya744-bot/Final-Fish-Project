package com.alsultanseafood.fishsupplychainmanagement.user.service;



import com.alsultanseafood
.fishsupplychainmanagement
.user.entity.User;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService
        extends UserDetailsService {

    User register(User user);

    String login(
            String email,
            String password,
            String role);

    User getUserById(
            Long id);

    List<User> getAllUsers();

    void deleteUser(
            Long id);



            List<User> getAllCustomers();

User updateCustomer(
        Long id,
        User user);

void deleteCustomer(
        Long id);
}