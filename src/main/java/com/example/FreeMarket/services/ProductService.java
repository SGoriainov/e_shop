package com.example.FreeMarket.services;

import com.example.FreeMarket.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    private  long ID = 0;

    {
        products.add(new Product(++ID,"PlayStation 5", "Simple desctiption", 6700, "Toliatti", "Sergey"));
        products.add(new Product(++ID,"CoreI9", "Simple desctiption", 1800, "Samara", "Petr"));
    }

    public  List<Product> listProducts() { return  products;}

    public  void saveProdust(Product product) {
        product.setId(++ID);
        products.add(product);
    }

    public  void deleteProduct (Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

}
