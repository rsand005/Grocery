package com.DailyGroceryShop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DailyGroceryShop.domain.Cart;
import com.DailyGroceryShop.domain.CartItem;
import com.DailyGroceryShop.domain.Product;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query("Update Cart cart set cart.listOfCartItems = :listOfCartItems where cart.user.userId =:userId "
	 * ) public int findCartByUser(@Param("userId") long
	 * userId, @Param("listOfCartItems") List<CartItem> listOfCartItems);
	 */
	/*
	 * @Query("Select p FROM Product p WHERE  p.category.id = :category ") public
	 * List<Product> findProductByCategory(@Param("category") Long category);
	 */
	@Query("Select c FROM Cart c WHERE c.user.userId =:user ")
	public Cart findCartByUser(@Param("user") Long user);
}
