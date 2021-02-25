package com.DailyGroceryShop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.DailyGroceryShop.domain.ProdInventory;

public interface ProdInventoryRepository extends JpaRepository<ProdInventory, Long>{

	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query("Insert into Prodinventory (id, discount, quantity) values (8, 6.99, 50) on Duplicate KEY UPDATE quantity = values(quantity)"
	 * ) public void updateProdInvetory(List<ProdInventory> listProdInventory);
	 */
}
