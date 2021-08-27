package com.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.product.model.Product;

@Service
public class ProductService {

	List<Product> productCollection = new ArrayList<Product>();

	public Long saveProduct(Product product) {
		productCollection.add(product);
		return product.getId();
	}

	public List<Product> searchById(Long id) {
		return productCollection.stream().filter(produt -> produt.getId().equals(id)).collect(Collectors.toList());
	}

	public List<Product> searchByName(String name) {
		return productCollection.stream().filter(produt -> produt.getName().equals(name)).collect(Collectors.toList());
	}

	public Long delete(Long id) {
		productCollection.removeIf(product -> product.getId().equals(product.getId()));
		return id;
	}

	public Product update(Product updateProduct) {
		Product productDate = productCollection.stream()
				.filter(product -> product.getId().equals(updateProduct.getId())).findFirst().get();
		productDate.setName(updateProduct.getName());
		productDate.setDescription(updateProduct.getDescription());

		return productDate;
	}

}
