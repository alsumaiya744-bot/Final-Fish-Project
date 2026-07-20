package com.alsultanseafood.fishsupplychainmanagement.Fish.controller;



import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;
import com.alsultanseafood.fishsupplychainmanagement.Fish.service.FishService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/api/fishes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class FishController {

    private final FishService fishService;

    public FishController(
            FishService fishService) {

        this.fishService =
                fishService;
    }

    

    @GetMapping
    public List<Fish> getAll() {

        return fishService.getAllFishes();
    }

    @GetMapping("/{id}")
    public Fish getById(
            @PathVariable Long id) {

        return fishService.getFishById(id);
    }

    @PutMapping("/{id}")
    public Fish update(
            @PathVariable Long id,
            @RequestBody Fish fish) {

        return fishService.updateFish(
                id,
                fish);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        fishService.deleteFish(id);
    }




 @PostMapping("/simple")
public Fish saveSimple(
        @RequestBody Fish fish) {

    return fishService.saveFish(fish);
}

    @PostMapping
public Fish saveFish(
        @RequestParam String fishName,
        @RequestParam String category,
        @RequestParam Double purchasePrice,
@RequestParam Double sellingPrice,
        @RequestParam Double availableStock,
        @RequestParam String storageType,
        @RequestParam MultipartFile image)
        throws IOException {

    String fileName =
            System.currentTimeMillis()
            + "_"
            + image.getOriginalFilename();

    Path uploadPath =
            Paths.get("uploads");

    if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
    }

    Path filePath =
            uploadPath.resolve(fileName);

    Files.copy(
            image.getInputStream(),
            filePath,
            StandardCopyOption.REPLACE_EXISTING);

    Fish fish = new Fish();

    fish.setFishName(fishName);
    fish.setCategory(category);
    
    fish.setPurchasePrice(
        purchasePrice);

fish.setSellingPrice(
        sellingPrice);
    fish.setAvailableStock(availableStock);
    fish.setStorageType(storageType);
    fish.setImagePath(fileName);

    return fishService.saveFish(fish);
}

@PostMapping("/upload")
public String uploadImage(
        @RequestParam("file")
        MultipartFile file)
        throws IOException {

    String fileName =
            System.currentTimeMillis()
            + "_"
            + file.getOriginalFilename();

    Path uploadPath =
            Paths.get("uploads");

    if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
    }

    Files.copy(
            file.getInputStream(),
            uploadPath.resolve(fileName),
            StandardCopyOption.REPLACE_EXISTING
    );

    return fileName;
}

}