package com.product.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.product.model.Product;

@Service
public class AsyncProductService {

	List<Product> productCollection = new CopyOnWriteArrayList<Product>();

	@Async
	public CompletableFuture<Long> saveProduct(Product product) {
		productCollection.add(product);
		return CompletableFuture.completedFuture(product.getId());
	}

	@Async
	public CompletableFuture<List<Product>> searchById(Long id) {
		List<Product> productList = productCollection.parallelStream().filter(produt -> produt.getId().equals(id))
				.collect(Collectors.toList());
		return CompletableFuture.completedFuture(productList);
	}

	@Async
	public CompletableFuture<List<Product>> searchByName(String name) {
		List<Product> productList = productCollection.parallelStream().filter(produt -> produt.getName().equals(name))
				.collect(Collectors.toList());
		return CompletableFuture.completedFuture(productList);
	}

	@Async
	public CompletableFuture<Long> delete(Long id) {
		productCollection.removeIf(product -> product.getId().equals(product.getId()));
		return CompletableFuture.completedFuture(id);
	}

	@Async
	public CompletableFuture<Product> update(Product updateProduct) {
		Product productDate = productCollection.parallelStream()
				.filter(product -> product.getId().equals(updateProduct.getId())).findFirst().get();
		productDate.setName(updateProduct.getName());
		productDate.setDescription(updateProduct.getDescription());

		return CompletableFuture.completedFuture(productDate);
	}

}
