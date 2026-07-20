package com.alsultanseafood.fishsupplychainmanagement.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alsultanseafood.fishsupplychainmanagement.dashboard.entity.Activity;

public interface ActivityRepository
        extends JpaRepository<
        Activity,
        Long> {

    List<Activity>
    findTop10ByOrderByDateDesc();
}
