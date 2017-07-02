package com.suning.springjpa.service;

import com.suning.springjpa.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> findProducts();
}