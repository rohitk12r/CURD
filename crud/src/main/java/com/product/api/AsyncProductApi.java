package com.product.api;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
import com.product.service.AsyncProductService;

@RestController
@RequestMapping("/v1/async/api")

public class AsyncProductApi {
	@Autowired
	private AsyncProductService productService;
	Logger log = LoggerFactory.getLogger(AsyncProductApi.class);

	@PostMapping(value = "/save")
	public Long saveProduct(@RequestBody Product product) {

		try {
			return productService.saveProduct(product).get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Getting error for saving the product", e);
		}
		return null;
	}

	@GetMapping(value = "/searchById/{productId}")
	public List<Product> searchById(@PathVariable(value = "productId") Long productId) {

		try {
			return productService.searchById(productId).get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Getting error for searchById the product", e);
		}
		return null;
	}

	@GetMapping(value = "/searchByName/{productName}")
	public List<Product> searchByName(@PathVariable(value = "productName") String productName) {

		try {
			return productService.searchByName(productName).get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Getting error for searchByName the product", e);
		}
		return null;
	}

	@DeleteMapping(value = "/delete/{productId}")
	public Long delete(@PathVariable(value = "productId") Long productId) {

		try {
			return productService.delete(productId).get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Getting error for delete the product", e);
		}
		return productId;
	}

	@PutMapping(value = "/update")
	public Product update(@RequestBody Product product) {

		try {
			return productService.update(product).get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Getting error for update the product", e);
		}
		return product;
	}
}
