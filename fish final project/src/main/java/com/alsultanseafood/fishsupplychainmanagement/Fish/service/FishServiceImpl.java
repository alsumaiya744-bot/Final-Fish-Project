package com.alsultanseafood.fishsupplychainmanagement.Fish.service;



import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;
import com.alsultanseafood.fishsupplychainmanagement.Fish.repository.FishRepository;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.entity.Activity;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.repository.ActivityRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FishServiceImpl
        implements FishService {

    private final FishRepository fishRepository;
    private final ActivityRepository activityRepository;

    public FishServiceImpl(
            FishRepository fishRepository,ActivityRepository activityRepository) {

        this.fishRepository =
                fishRepository;
                this.activityRepository =
            activityRepository;
    }

    @Override
public Fish saveFish(Fish fish) {

    Fish savedFish =
            fishRepository.save(fish);

    activityRepository.save(
            new Activity(
                    "Fish Added",
                    LocalDateTime.now()
            )
    );

    return savedFish;
}
    @Override
    public List<Fish> getAllFishes() {

        return fishRepository.findAll();
    }

    @Override
    public Fish getFishById(Long id) {

        return fishRepository.findById(id)
                .orElseThrow(
                        () ->
                                new RuntimeException(
                                        "Fish Not Found"));
    }

    @Override
    public Fish updateFish(
            Long id,
            Fish fish) {

        Fish db =
                getFishById(id);

        db.setFishName(
                fish.getFishName());

        db.setCategory(
                fish.getCategory());

        db.setPurchasePrice(
        fish.getPurchasePrice());

db.setSellingPrice(
        fish.getSellingPrice());

        db.setAvailableStock(
                fish.getAvailableStock());

        db.setStorageType(
                fish.getStorageType());

        db.setImagePath(
                fish.getImagePath());

        return fishRepository.save(db);
    }

   @Override
public void deleteFish(Long id) {
    fishRepository.deleteById(id);
}

    @Override
    public void reduceStock(
            Long fishId,
            Double quantity) {

        Fish fish =
                getFishById(fishId);

        double stock =
                fish.getAvailableStock()
                        - quantity;

        if (stock < 0) {
            stock = 0;
        }

        fish.setAvailableStock(stock);

        fishRepository.save(fish);
    }

    @Override
    public void increaseStock(
            Long fishId,
            Double quantity) {

        Fish fish =
                getFishById(fishId);

        fish.setAvailableStock(
                fish.getAvailableStock()
                        + quantity);

        fishRepository.save(fish);

        
    }
    
}