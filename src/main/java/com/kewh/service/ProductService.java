package com.kewh.service;

import java.util.List;

import com.kewh.entity.Product;

public interface ProductService {
    List<Product> findProducts();
}