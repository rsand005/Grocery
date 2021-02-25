package com.DailyGroceryShop.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DailyGroceryShop.domain.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
	
	@Modifying
	@Transactional
	@Query("Update CartItem c set  c.quantity = :quantity + c.quantity WHERE c.product.productId =:productId")
	public int findCartItemByProduct(@Param("quantity") int quantity, @Param("productId") int productId);
	
	
	//public void 
	/*
	 * @Query("Select c FROM CartItem c WHERE c.product.productId =:productId")
	 * public CartItem findCartItemByProduct(@Param("productId") int productId);
	 */
}
