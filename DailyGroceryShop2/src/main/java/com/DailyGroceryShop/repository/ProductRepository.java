package com.DailyGroceryShop.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.DailyGroceryShop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("Select p FROM Product p WHERE  p.prodName LIKE CONCAT('%',:prodName,'%')")
	public List<Product> findProductByProdName(@Param("prodName") String prodName);
	
	@Query("Select p FROM Product p WHERE  p.category.id = :category ")
	public List<Product> findProductByCategory(@Param("category") Long category);
	
	@Query("Select p FROM Product p WHERE  p.prodName LIKE CONCAT('%',:prodName,'%') OR p.category.id = :category")
	public List<Product> findProductByProdNameOrByCategory(@Param("prodName") String prodName , @Param("category") Long category);
	
	public Product findProductByProductId(int productId);
	

}
