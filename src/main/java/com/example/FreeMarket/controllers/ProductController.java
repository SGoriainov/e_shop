package com.example.FreeMarket.controllers;

import com.example.FreeMarket.models.Product;
import com.example.FreeMarket.models.User;
import com.example.FreeMarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public Map<String, Object> products(@RequestParam(name = "searchWord", required = false) String title, Principal principal) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("products", productService.listProducts(title));
        responseData.put("user", productService.getUserByPrincipal(principal));
        responseData.put("searchWord", title);

        return responseData;
    }

    @GetMapping("/product/{id}")
    public Map<String, Object> productInfo(@PathVariable Long id, Principal principal) {
        Product product = productService.getProductById(id);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("user", productService.getUserByPrincipal(principal));
        responseData.put("product", product);
        responseData.put("images", product.getImages());
        responseData.put("authorProduct", product.getUser());
        return responseData;
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/my/products";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        productService.deleteProduct(productService.getUserByPrincipal(principal), id);
        return "redirect:/my/products";
    }

    @GetMapping("/my/products")
    public Map<String, Object> userProducts(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("user", user);
        responseData.put("products", user.getProducts());
        return responseData;
    }
}