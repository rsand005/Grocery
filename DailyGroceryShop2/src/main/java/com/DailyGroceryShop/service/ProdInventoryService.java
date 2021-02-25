package com.DailyGroceryShop.service;

import java.util.List;

import com.DailyGroceryShop.domain.ProdInventory;
import com.DailyGroceryShop.domain.Product;

public interface ProdInventoryService {
	
	public ProdInventory save(ProdInventory prodInventory);
	public void updateProdInvetory(List<ProdInventory> listProdInventory);
}
