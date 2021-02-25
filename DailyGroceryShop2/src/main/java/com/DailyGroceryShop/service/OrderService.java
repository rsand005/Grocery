package com.DailyGroceryShop.service;

import java.util.List;

import com.DailyGroceryShop.domain.Order;
import com.DailyGroceryShop.domain.OrderedProd;

public interface OrderService {
	
	public Order saveOrder(Order order);
	public List<Order> getOrdersByCustomer(long customerId);

}
