package com.DailyGroceryShop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DailyGroceryShop.domain.CartItem;
import com.DailyGroceryShop.domain.Product;
import com.DailyGroceryShop.repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartItemRepository cartItemRepository;

	@Override
	public CartItem save(CartItem cartItem) {
		CartItem cItem = cartItemRepository.save(cartItem);
		return cItem;
	}

	@Override
	public List<CartItem> getAllCartItem() {
		List<CartItem> listOfCartItems = cartItemRepository.findAll();
		return listOfCartItems;
	}

	@Transactional
	@Override
	public List<CartItem> findAndUpdateCartItemQty(List<CartItem> cartItemList) {
		List<CartItem> listOfCartItems = new ArrayList<CartItem>();
		int id = 0;
		System.out.println("findAndUpdateCartItemQty " + cartItemList);
		//cartItemRepository.deleteInBatch(cartItemList);
		listOfCartItems.addAll(cartItemRepository.saveAll(cartItemList));
		
		/*
		 * for (CartItem cartItem : cartItemList) {
		 * System.out.println("findAndUpdateCartItemQty cartItem.getQuantity() "+
		 * cartItem.getQuantity()); if(cartItem.getId() == 0) {
		 * listOfCartItems.add(cartItemRepository.save(cartItem)); }else { CartItem
		 * cItem = cartItemRepository.findById(cartItem.getId()).get();
		 * System.out.println("inside findAndUpdateCartItemQty  cItem " + cItem);
		 * cItem.setQuantity(cartItem.getQuantity()); //CartItem cSAved =
		 * cartItemRepository.save(cItem);
		 * //System.out.println("cartItemRepository.save(cItem)  cSAved " + cSAved);
		 * listOfCartItems.add(cartItemRepository.save(cItem)); } }
		 */
		return listOfCartItems;
	}

	@Override
	public void deleteCartItemByCartItemId(List<CartItem> listCartItems) {
		System.out.println("inside CartItemServiceImpl deleteCartItemByCartItemId  " + listCartItems);
		//cartItemRepository.deleteAll(listCartItems);
		cartItemRepository.deleteInBatch(listCartItems);
	}

	@Override
	public void emptyAndSaveCartItemByCart(int cartId) {
		// cartItemRepository.de

	}

	@Override
	public List<CartItem> saveListOfCartItems(List<CartItem> listOfCartItems) {
		List<CartItem> listCartItems = cartItemRepository.saveAll(listOfCartItems);
		return listCartItems;
	}

}
