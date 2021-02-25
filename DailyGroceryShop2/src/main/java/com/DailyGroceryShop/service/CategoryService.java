package com.DailyGroceryShop.service;

import java.util.List;

import com.DailyGroceryShop.domain.Category;

public interface CategoryService {
	
	public List<Category> getListOfCategory();
	public Category findCategroyBytype(String type);

}
