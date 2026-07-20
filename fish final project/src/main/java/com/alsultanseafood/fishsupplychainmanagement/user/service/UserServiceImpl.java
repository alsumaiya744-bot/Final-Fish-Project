package com.alsultanseafood.fishsupplychainmanagement.user.service;



import com.alsultanseafood.fishsupplychainmanagement.dashboard.repository.ActivityRepository;
import com.alsultanseafood
.fishsupplychainmanagement
.user.entity.User;
import com.alsultanseafood
.fishsupplychainmanagement
.user.repository.UserRepository;
import com.alsultanseafood
.fishsupplychainmanagement.user
.security.JwtUtil;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.entity.Activity;
import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl
        implements UserService {

    private final UserRepository
            userRepository;

    private final PasswordEncoder
            passwordEncoder;

    private final JwtUtil
            jwtUtil;
            private final ActivityRepository activityRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil, ActivityRepository activityRepository) {

        this.userRepository =
                userRepository;

        this.passwordEncoder =
                passwordEncoder;

        this.jwtUtil =
                jwtUtil;
                this.activityRepository =
            activityRepository;
    }

    // ================= Register =================

    @Override
public User register(
        User user) {

    if (userRepository
            .findByEmail(
                    user.getEmail())
            .isPresent()) {

        throw new RuntimeException(
                "Email Already Exists");
    }

    user.setPassword(
            passwordEncoder.encode(
                    user.getPassword()));

    user.setRole("USER");

    user.setActive(true);

    User savedUser =
            userRepository.save(user);

    activityRepository.save(
            new Activity(
                    "Customer Registered",
                    LocalDateTime.now()
            )
    );

    return savedUser;
}

    // ================= Login =================

    @Override
    public String login(
            String email,
            String password,
            String role) {

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Invalid Email"));

                                                

        if (!user.getRole()
                .equals(role)) {

            throw new RuntimeException(
                    "Invalid Role");
        }

        boolean match =
                passwordEncoder.matches(
                        password,
                        user.getPassword());

        if (!match) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        return jwtUtil.generateToken(
        user.getEmail(),
        user.getRole());
    }

    // ================= Load User =================

    @Override
    public UserDetails
    loadUserByUsername(
            String email) {

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new UsernameNotFoundException(
                                                "User Not Found"));

        return org.springframework
                .security
                .core
                .userdetails
                .User
                .withUsername(
                        user.getEmail())
                .password(
                        user.getPassword())
                .roles(
                        user.getRole())
                .build();
    }

    // ================= Get By Id =================

    @Override
    public User getUserById(
            Long id) {

        return userRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new RuntimeException(
                                        "User Not Found"));
    }

    // ================= Get All =================

    @Override
    public List<User>
    getAllUsers() {

        return userRepository
                .findAll();
    }

    // ================= Delete =================

    @Override
    public void deleteUser(
            Long id) {

        User user =
                getUserById(id);

        user.setActive(false);

        userRepository.save(user);
    }

   




    @Override
public List<User>
getAllCustomers() {

    return userRepository
            .findByRoleAndActive(
                    "USER",
                    true);
}

@Override
public User updateCustomer(
        Long id,
        User user) {

    User db =
            getUserById(id);

    db.setFullName(
            user.getFullName());

    db.setPhoneNumber(
            user.getPhoneNumber());

    db.setEmail(
            user.getEmail());

    db.setAddress(
            user.getAddress());

    db.setCustomerType(
            user.getCustomerType());

    return userRepository
            .save(db);
}

@Override
public void deleteCustomer(
        Long id) {

    User user =
            getUserById(id);

    user.setActive(false);

    userRepository.save(user);
}
}