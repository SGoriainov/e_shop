package com.example.FreeMarket.services;

import com.example.FreeMarket.models.Product;
import com.example.FreeMarket.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> listProducts (String title) {
        if(title != null) productRepository.findByTitle(title);
        return productRepository.findAll();
    }


    public  void saveProdust(Product product) {
        product.setId(++ID);
        products.add(product);
    }

    public  void deleteProduct (Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    public  Product getProductById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) return product;
        }
        return  null;
    }
}
