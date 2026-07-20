package com.alsultanseafood.fishsupplychainmanagement.Fish.service;



import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;

import java.util.List;

public interface FishService {

    Fish saveFish(Fish fish);

    List<Fish> getAllFishes();

    Fish getFishById(Long id);

    Fish updateFish(
            Long id,
            Fish fish);

    void deleteFish(Long id);

    void reduceStock(
            Long fishId,
            Double quantity);

    void increaseStock(
            Long fishId,
            Double quantity);
}