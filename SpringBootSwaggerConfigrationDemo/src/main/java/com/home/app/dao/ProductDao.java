package com.home.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.app.model.Product;

public interface ProductDao  extends JpaRepository<Product, Integer>{

}
