package com.alsultanseafood.fishsupplychainmanagement.user.repository;



import com.alsultanseafood
.fishsupplychainmanagement
.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(
            String email);

    Optional<User> findByPhoneNumber(
            String phoneNumber);

    List<User> findByRole(
        String role);


        Long countByRole(
        String role);

List<User> findByRoleAndActive(
        String role,
        Boolean active);

    boolean existsByEmail(
            String email);

    boolean existsByPhoneNumber(
            String phoneNumber);
}