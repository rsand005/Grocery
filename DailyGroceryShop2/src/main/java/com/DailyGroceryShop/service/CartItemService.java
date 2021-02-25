package com.DailyGroceryShop.service;

import java.util.List;

import com.DailyGroceryShop.domain.CartItem;

public interface CartItemService {
	
	public CartItem save(CartItem cartItem);
	public List<CartItem> getAllCartItem();
	public List<CartItem> findAndUpdateCartItemQty(List<CartItem> cartItemList);
	public void deleteCartItemByCartItemId(List<CartItem> listOfCartItems);
	public void emptyAndSaveCartItemByCart(int cartId);
	public List<CartItem> saveListOfCartItems(List<CartItem> listOfCartItems);

}
