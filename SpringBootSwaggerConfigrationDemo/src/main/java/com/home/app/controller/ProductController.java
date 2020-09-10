package com.home.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.app.model.Product;
import com.home.app.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
@Api(value="onlinestore")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@ApiOperation(value = "View a list of available products",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping(value = "/list", produces = "application/json")
    public List<Product> list(){
		List<Product> productList = productService.listAllProducts();
        return productList;
    }
	
	@ApiOperation(value = "Search a product with an ID",response = Product.class)
    @GetMapping(value = "/show/{id}", produces = "application/json")
    public Product showProduct(@PathVariable Integer id){
       Product product = productService.getProductById(id);
        return product;
    }
	
	@ApiOperation(value = "Add a product")
    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<String> saveProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return new ResponseEntity<String>("Product saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a product")
    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Product product){
        Product storedProduct = productService.getProductById(id);
        storedProduct.setDescription(product.getDescription());
        storedProduct.setImageUrl(product.getImageUrl());
        storedProduct.setPrice(product.getPrice());
        productService.saveProduct(storedProduct);
        return new ResponseEntity<String>("Product updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a product")
    @DeleteMapping(value="/delete/{id}", produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        productService.deleteProduct(id);
        return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);

    }

}
