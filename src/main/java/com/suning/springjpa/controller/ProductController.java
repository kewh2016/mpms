package com.suning.springjpa.controller;

import com.suning.springjpa.dao.ProductDao;
import com.suning.springjpa.entity.Product;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @RequestMapping({ "/productManage" })
    public String name(Model model) {
	List<Product> products = this.productDao.findAll();
	model.addAttribute("products", products);
	return "productManage";
    }

    @RequestMapping({ "/addProduct" })
    public String addProduct(Model model) {
	return "addProduct";
    }

    @RequestMapping({ "/addProductCommit" })
    public String addProductCommit(Product product) {
	product.setCreateTime(new Date());
	this.productDao.save(product);
	return "redirect:/productManage";
    }

    @RequestMapping({ "/editProduct" })
    public String addProduct(Long id, Model model) {
	model.addAttribute("pro", this.productDao.findOne(id));
	return "addProduct";
    }
}