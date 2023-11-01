package com.example.FreeMarket.controllers;

import com.example.FreeMarket.models.Image;
import com.example.FreeMarket.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        if (image != null) {
            return ResponseEntity.ok(image); // Возвращаем успешный ответ с объектом Image
        } else {
            return ResponseEntity.notFound().build(); // Возвращаем ответ "Not Found", если изображение не найдено
        }
    }
}
