package com.DailyGroceryShop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DailyGroceryShop.domain.Order;
import com.DailyGroceryShop.domain.OrderedProd;
import com.DailyGroceryShop.repository.OrderRespository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRespository orderRespository;
	
	@Override
	public Order saveOrder(Order order) {
		Order ordered  =orderRespository.save(order);
		return ordered;
	}

	
	  @Override public List<Order> getOrdersByCustomer(long customerId) {
      System.out.println("OrderServiceImpl getOrdersByCustomer  customerId "+ customerId); 
	  List<Order> listOfOrders = orderRespository.getOrdersByCustomer(customerId);
	  System.out.println("OrderServiceImpl listOfOrders.size() "+ listOfOrders.size()); 
	  return listOfOrders; 
	  }

	 

}
