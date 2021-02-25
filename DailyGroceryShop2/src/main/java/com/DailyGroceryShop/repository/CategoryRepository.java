package com.DailyGroceryShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DailyGroceryShop.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	//public List<Category> findAll();
	
	public Category findCategroyBytype(String type);
}
