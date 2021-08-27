package com.product.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/v1/api")
public class ProductApi {
	Logger log = LoggerFactory.getLogger(ProductApi.class);
	@Autowired
	private ProductService productService;

	@PostMapping(value = "/save")
	public Long saveProduct(@RequestBody Product product) {

		return productService.saveProduct(product);
	}

	@GetMapping(value = "/searchById/{productId}")
	public List<Product> searchById(@PathVariable(value = "productId") Long productId) {

		return productService.searchById(productId);
	}

	@GetMapping(value = "/searchByName/{productName}")
	public List<Product> searchByName(@PathVariable(value = "productName") String productName) {

		return productService.searchByName(productName);
	}

	@DeleteMapping(value = "/delete/{productId}")
	public Long delete(@PathVariable(value = "productId") Long productId) {

		return productService.delete(productId);
	}

	@PutMapping(value = "/update")
	public Product update(@RequestBody Product product) {

		return productService.update(product);
	}

}
