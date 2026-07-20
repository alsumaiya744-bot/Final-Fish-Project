package com.alsultanseafood.fishsupplychainmanagement.user.controller;

import com.alsultanseafood.fishsupplychainmanagement.user.entity.User;
import com.alsultanseafood.fishsupplychainmanagement.user.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CustomerController {

    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getCustomers() {
        return userService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public User getCustomer(
            @PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateCustomer(
            @PathVariable Long id,
            @RequestBody User user) {

        return userService.updateCustomer(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(
            @PathVariable Long id) {

        userService.deleteCustomer(id);
    }
}