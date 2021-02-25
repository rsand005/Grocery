package com.DailyGroceryShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DailyGroceryShop.domain.OrderedProd;
import com.DailyGroceryShop.repository.OrderedProdRepository;

@Service
public class OrderedProdSreviceImpl implements OrderedProdService{
	
	@Autowired
	OrderedProdRepository orderedProdRepository;

	@Override
	public OrderedProd updateReturnedOrderedProdById(int id) {
		OrderedProd orderedProd = orderedProdRepository.findById(id).get();
		orderedProd.setFinalCost(0);
		orderedProd.setStatus("RETURNED");
		OrderedProd op = orderedProdRepository.save(orderedProd);
		return op;
	}

	
}
