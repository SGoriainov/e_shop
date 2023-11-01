package com.example.FreeMarket.controllers;

import com.example.FreeMarket.models.Product;
import com.example.FreeMarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.listProducts();
    }

    @GetMapping("/product/{id}")
    public Map<String, Object> productInfo(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("product", product);
        responseData.put("images", product.getImages());

        return responseData;
    }

    @PostMapping("/product/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}