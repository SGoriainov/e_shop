package com.example.FreeMarket.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private  Long id;
    private  String title;
    private  String descriprion;
    private int price;
    private  String city;
    private  String author;
}
