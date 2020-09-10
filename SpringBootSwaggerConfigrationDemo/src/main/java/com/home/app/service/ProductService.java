package com.home.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.app.dao.ProductDao;
import com.home.app.model.Product;

@Service
public class ProductService {

private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    
@Autowired
    private ProductDao productRepository;

    public List<Product> listAllProducts() {
        logger.debug("listAllProducts called");
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        logger.debug("getProductById called");
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        logger.debug("saveProduct called");
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        logger.debug("deleteProduct called");
        productRepository.deleteById(id);
    }
}
