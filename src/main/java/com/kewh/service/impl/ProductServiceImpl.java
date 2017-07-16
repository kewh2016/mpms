package com.kewh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kewh.dao.ProductDao;
import com.kewh.entity.Product;
import com.kewh.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> findProducts() {
	List<Product> products = this.productDao.findAll();
	products.removeIf((t) -> {
	    return t.getStock().intValue() <= 0;
	});
	return products;
    }
}