package com.kewh.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String name;
    private String model;
    private Integer stock;
    private Integer point;
    private BigDecimal price;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
	return this.id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getBrand() {
	return this.brand;
    }

    public void setBrand(String brand) {
	this.brand = brand;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getModel() {
	return this.model;
    }

    public void setModel(String model) {
	this.model = model;
    }

    public Integer getStock() {
	return this.stock;
    }

    public void setStock(Integer stock) {
	this.stock = stock;
    }

    public Integer getPoint() {
	return this.point;
    }

    public void setPoint(Integer point) {
	this.point = point;
    }

    public Date getCreateTime() {
	return this.createTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public Date getUpdateTime() {
	return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
    }

    public BigDecimal getPrice() {
	return this.price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }
}