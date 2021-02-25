package com.DailyGroceryShop.dao;

import java.util.List;

import com.DailyGroceryShop.domain.ProdInventory;

public interface ProdInventoryDao {
	
	public void updateProdInvetoryBatch(List<ProdInventory> listProdInventory);

}
