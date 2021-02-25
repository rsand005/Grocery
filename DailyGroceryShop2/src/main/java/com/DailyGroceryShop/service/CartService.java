package com.DailyGroceryShop.service;

import java.util.List;

import com.DailyGroceryShop.domain.Cart;
import com.DailyGroceryShop.domain.CartItem;
import com.DailyGroceryShop.domain.User;

public interface CartService {
	
	public void saveAndUpdateCart(User user, List<CartItem> listCartItems );
	public Cart getCartByUser(Long userId);
	public void deleteCartByUser(User user);
	public void saveCart(Cart cart);
}
