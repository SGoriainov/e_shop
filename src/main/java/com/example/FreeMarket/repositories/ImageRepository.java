package com.example.FreeMarket.repositories;

import com.example.FreeMarket.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository <Image,Long> {
}
