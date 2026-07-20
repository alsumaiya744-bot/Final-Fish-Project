package com.alsultanseafood.fishsupplychainmanagement.Fish.repository;

import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository
        extends JpaRepository<Fish, Long> {
}