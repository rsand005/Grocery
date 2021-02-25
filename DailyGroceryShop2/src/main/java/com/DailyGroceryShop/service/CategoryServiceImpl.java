package com.DailyGroceryShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.DailyGroceryShop.domain.Category;

@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	com.DailyGroceryShop.repository.CategoryRepository CategoryRepository;
	
	@Override
	public List<Category> getListOfCategory() {
		List<Category> listOfCategory = CategoryRepository.findAll();
		System.out.println(" CategoryServiceImpl listOfCategory.size()  " + listOfCategory.size());
		return listOfCategory;
	}

	@Override
	public Category findCategroyBytype(String type) {
		Category category = CategoryRepository.findCategroyBytype(type);
		return category;
	}

	
	

}
