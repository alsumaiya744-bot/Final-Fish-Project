package com.alsultanseafood.fishsupplychainmanagement.Fish.controller;



import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/uploads")
public class ImageController {

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getImage(
            @PathVariable String fileName)
            throws Exception {

        Path path =
                Paths.get("uploads")
                        .resolve(fileName);

        Resource resource =
                new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .body(resource);
    }
}
