package com.DailyGroceryShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DailyGroceryShop.domain.Cart;
import com.DailyGroceryShop.domain.CartItem;
import com.DailyGroceryShop.domain.User;
import com.DailyGroceryShop.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Override
	public void saveAndUpdateCart(User user, List<CartItem> listCartItems) {
		System.out.println("saveAndUpdateCart listCartItemss " + listCartItems);

		/*
		 * Cart updateCart = cartRepository.findCartByUser(user.getUserId());
		 * System.out.println("saveAndUpdateCart findCartByUser " + updateCart);
		 * if(updateCart != null) { cartRepository.delete(updateCart); }
		 */
		//cartRepository.delete(updateCart);
		Cart newCart = new Cart();
		newCart.setListOfCartItems(listCartItems);
		newCart.setUser(user);
		Cart savedCart = cartRepository.save(newCart);
		/*
		 * if (updateCart != null) { updateCart.setListOfCartItems(listCartItems); Cart
		 * savedCart = cartRepository.save(updateCart); return; } else { Cart newCart =
		 * new Cart(); newCart.setListOfCartItems(listCartItems); newCart.setUser(user);
		 * Cart savedCart = cartRepository.save(newCart); }
		 */

	}

	@Override
	public Cart getCartByUser(Long userId) {
		Cart cart = cartRepository.findCartByUser(userId);
		System.out.println("getCartByUser " + cart);
		return cart;
	}

	@Override
	public void deleteCartByUser(User user) {
		Cart cart = cartRepository.findCartByUser(user.getUserId());
		if(cart != null) {
			cartRepository.delete(cart);
		}
		
		
	}

	@Override
	public void saveCart(Cart cart) {
		cartRepository.save(cart);
		
	}

}
