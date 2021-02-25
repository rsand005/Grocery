package com.DailyGroceryShop.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.DailyGroceryShop.domain.Product;

public interface ProductService {

	public void saveProduct(Product product);
	public List<Product> listOfProducts();
	public void deleteProductById(int productId);
	public Product findProductByProductId(int productId);
	public void updateProduct(Product product);
	public List<Product> findProductByProdName(String prodName);
	public List<Product> findProductByCategory(Long categoryId);
	public List<Product> findProductByProdNameOrByCategory(String prodName ,Long category);
}
