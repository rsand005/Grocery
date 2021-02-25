package com.DailyGroceryShop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DailyGroceryShop.domain.ProdInventory;
import com.DailyGroceryShop.domain.Product;
import com.DailyGroceryShop.repository.ProdInventoryRepository;

@Service
@Transactional
public class ProdInventoryServiceImpl implements ProdInventoryService{
	
	@Autowired
	ProdInventoryRepository prodInventoryRepository;
	
	@Override
	public ProdInventory save(ProdInventory prodInventory) {
		ProdInventory prodInventoryObj = prodInventoryRepository.save(prodInventory);
		return prodInventoryObj;
	}

	@Override
	public void updateProdInvetory(List<ProdInventory> listProdInventory) {
		prodInventoryRepository.saveAll(listProdInventory);
	}

	

}
